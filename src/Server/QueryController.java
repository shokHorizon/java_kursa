package Server;


import DAO.*;
import Models.*;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryController {
   public static Packet<? extends Model> query_request(Packet<? extends Model> packet){
       // Отбрасывание пакетов без указания модели
       if (packet.getQueryModel() == null) return packet;

       int token = packet.getToken();
       System.out.println("Пришедший токен " + token);

       // Минимизация дальнейшей работы путем раннего создания пакета
       List<Model> response_models = new ArrayList<>();
       Packet<? extends Model> response_packet = new Packet<>(null, null, response_models);

        switch (packet.getQueryModel()){
            case Users->{
                if (packet.getModels() == null){
                    System.out.println("Есть доступ? " + (packet.getQueryMethod() == QueryMethod.Read && AccessManager.hasRequiredAccess(token, 2))
                    + " Читаем? " + (packet.getQueryMethod() == QueryMethod.Read) + " Только доступ? " + AccessManager.getAccessLevel(token));
                    // Чтобы получить ВСЕХ пользователей, фильтры не нужны -> модель не передается
                    if (packet.getQueryMethod() == QueryMethod.Read && AccessManager.hasRequiredAccess(token, 2))
                        response_models.addAll(UsersDao.INSTANCE.getAll());
                    return response_packet;
                }
                System.out.println("Модель пришла!");
                Users user = (Users) packet.getModels().get(0);
                // Вторичное хэширование пароля
                user.hashPassword();

                if (packet.getQueryMethod() != null) {
                    switch (packet.getQueryMethod()) {
                        case Read -> {
                            if (AccessManager.hasRequiredAccess(token, 1)) {
                                // Не позволяем менеджеру фильтровать по паролю и уровню доступа
                                if (!AccessManager.hasRequiredAccess(token, 2)){
                                    user.setHashedPassword(0);
                                    user.setAccessLevel(-1);
                                }
                                response_models.addAll(UsersDao.INSTANCE.get(user));
                            }
                        }
                        case Update -> {
                            if (AccessManager.hasRequiredAccess(token, 1))
                                UsersDao.INSTANCE.update(user);
                        }
                        case Create -> {
                            if (AccessManager.hasRequiredAccess(token, 2))
                                UsersDao.INSTANCE.save(user);
                        }
                        case Delete -> {
                            if (AccessManager.hasRequiredAccess(token, 2))
                                UsersDao.INSTANCE.delete(user.getId());
                        }
                    }
                    break;
                }
                Optional<Users> db_user = UsersDao.INSTANCE.getByLogin(user.getLogin());
                // Ставим доступ -1 модели, которую передал пользователь
                user.setAccessLevel(-1);
                // Передаём пользователю корректную модель из бд с нормальным уровнем доступа,
                // либо отправляем ему свою же модель, но с уровнем доступа -1
                user = db_user.orElse(user);
                response_models.add(user);
                System.out.println("User.getHashedPassword() = " + user.getHashedPassword() + "  " + user.getAccessLevel());
                // Добавляем токен, если пользователь успешно авторизовался
                if (user.getAccessLevel() >= 0) {
                    System.out.println("user has access level: " + user.getAccessLevel());
                    user.setHashedPassword(AccessManager.generateToken(user.getLogin(), user.getHashedPassword(), user.getAccessLevel()));
                }
            }
            case Books->{
                if (packet.getModels() == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(BooksDao.INSTANCE.getAll());
                    return response_packet;
                }
                Books book = (Books) packet.getModels().get(0);
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        if (AccessManager.hasRequiredAccess(token, 0)) {
                            response_models.addAll(BooksDao.INSTANCE.get(book));
                        }
                    }
                    case Update -> {
                        if (AccessManager.hasRequiredAccess(token, 1)) {
                            if (!AccessManager.hasRequiredAccess(token, 2)){
                                book.setId(0);
                                book.setTravel(0);
                                book.setUser(0);
                            }
                            BooksDao.INSTANCE.update(book);
                        }
                    }
                    case Create -> {
                        System.out.println("AM access: " + AccessManager.getAccessLevel(token) + " ");
                        if (AccessManager.hasRequiredAccess(token, 0))
                            BooksDao.INSTANCE.save(book);
                        else
                            System.out.println("Ебать ты лох " + token + " " + AccessManager.getAccessLevel(token));
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 0)) {
                            BooksDao.INSTANCE.delete(book.getId());
                        }
                    }
                }
            }
            case Countries->{
                if (packet.getModels() == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(CountriesDao.INSTANCE.getAll());
                    return response_packet;
                }
                Countries country = (Countries) packet.getModels().get(0);
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        response_models.addAll(CountriesDao.INSTANCE.get(country));
                    }
                    case Update -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            CountriesDao.INSTANCE.update(country);
                    }
                    case Create -> {
                        if (AccessManager.hasRequiredAccess(token, 1))
                            CountriesDao.INSTANCE.save(country);
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            CountriesDao.INSTANCE.delete(country.getId());
                    }
                }
            }
            case Travels->{
                if (packet.getModels() == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(TravelsDao.INSTANCE.getAll());
                    return response_packet;
                }
                Travels travel = (Travels) packet.getModels().get(0);
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        response_models.addAll(TravelsDao.INSTANCE.get(travel));
                    }
                    case Update -> {
                        if (AccessManager.hasRequiredAccess(token, 1))
                            TravelsDao.INSTANCE.update(travel);
                    }
                    case Create -> {
                        if (AccessManager.hasRequiredAccess(token, 1))
                            TravelsDao.INSTANCE.save(travel);
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 1))
                            TravelsDao.INSTANCE.delete(travel.getId());
                    }
                }
            }
            case TravelTypes->{
                if (packet.getModels() == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(TravelTypesDao.INSTANCE.getAll());
                    return response_packet;
                }
                TravelTypes travelType = (TravelTypes) packet.getModels().get(0);
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        response_models.addAll(TravelTypesDao.INSTANCE.get(travelType));
                    }
                    case Update -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            TravelTypesDao.INSTANCE.update(travelType);
                    }
                    case Create -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            TravelTypesDao.INSTANCE.save(travelType);
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            TravelTypesDao.INSTANCE.delete(travelType.getId());
                    }
                }
            }
            case Cities->{
                if (packet.getModels() == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(CitiesDao.INSTANCE.getAll());
                    return response_packet;
                }
                Cities city = (Cities) packet.getModels().get(0);
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        response_models.addAll(CitiesDao.INSTANCE.get(city));
                    }
                    case Update -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            CitiesDao.INSTANCE.update(city);
                    }
                    case Create -> {
                        if (AccessManager.hasRequiredAccess(token, 1))
                            CitiesDao.INSTANCE.save(city);
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            CitiesDao.INSTANCE.delete(city.getId());
                    }
                }
            }
        }
        return response_packet;
    }
}
