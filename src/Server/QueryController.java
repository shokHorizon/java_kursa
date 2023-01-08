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

       // Минимизация дальнейшей работы путем раннего создания пакета
       List<Model> response_models = new ArrayList<>();
       Packet<? extends Model> response_packet = new Packet<>(null, null, response_models);

        switch (packet.getQueryModel()){
            case Users->{
                Users user = (Users) packet.getModels().get(0);
                if (user == null){
                    // Чтобы получить ВСЕХ пользователей, фильтры не нужны -> модель не передается
                    if (packet.getQueryMethod() == QueryMethod.Read && AccessManager.hasRequiredAccess(token, 2))
                        response_models.addAll(UsersDao.INSTANCE.getAll());
                    return response_packet;
                }
                // Вторичное хэширование пароля
                user.hashPassword();

                if (packet.getQueryMethod() != null) {
                    switch (packet.getQueryMethod()) {
                        case Read -> {
                            if (AccessManager.hasRequiredAccess(token, 1))
                                response_models.addAll(UsersDao.INSTANCE.get(user));
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
                // Добавляем токен, если пользователь успешно авторизовался
                if (user.getAccessLevel() >= 0)
                    user.setHashedPassword(AccessManager.generateToken(user.getLogin(), user.getHashedPassword(), user.getAccessLevel()));
            }
            case Books->{
                Books book = (Books) packet.getModels().get(0);
                if (book == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(CountriesDao.INSTANCE.getAll());
                    return response_packet;
                }
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        response_models.addAll(BooksDao.INSTANCE.get(book));
                    }
                    case Update -> {
                        BooksDao.INSTANCE.update(book);
                    }
                    case Create -> {
                        BooksDao.INSTANCE.save(book);
                    }
                    case Delete -> {
                        BooksDao.INSTANCE.delete(book.getId());
                    }
                }
            }
            case Countries->{
                Countries country = (Countries) packet.getModels().get(0);
                if (country == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(CountriesDao.INSTANCE.getAll());
                    return response_packet;
                }
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        response_models.addAll(CountriesDao.INSTANCE.get(country));
                    }
                    case Update -> {
                        CountriesDao.INSTANCE.update(country);
                    }
                    case Create -> {
                        CountriesDao.INSTANCE.save(country);
                    }
                    case Delete -> {
                        CountriesDao.INSTANCE.delete(country.getId());
                    }
                }
            }
            case Travels->{
                Travels travel = (Travels) packet.getModels().get(0);
                if (travel == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(TravelsDao.INSTANCE.getAll());
                    return response_packet;
                }
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        response_models.addAll(TravelsDao.INSTANCE.get(travel));
                    }
                    case Update -> {
                        TravelsDao.INSTANCE.update(travel);
                    }
                    case Create -> {
                        TravelsDao.INSTANCE.save(travel);
                    }
                    case Delete -> {
                        TravelsDao.INSTANCE.delete(travel.getId());
                    }
                }
            }
            case TravelTypes->{
                TravelTypes travelType = (TravelTypes) packet.getModels().get(0);
                if (travelType == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(TravelTypesDao.INSTANCE.getAll());
                    return response_packet;
                }
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        response_models.addAll(TravelTypesDao.INSTANCE.get(travelType));
                    }
                    case Update -> {
                        TravelTypesDao.INSTANCE.update(travelType);
                    }
                    case Create -> {
                        TravelTypesDao.INSTANCE.save(travelType);
                    }
                    case Delete -> {
                        TravelTypesDao.INSTANCE.delete(travelType.getId());
                    }
                }
            }
            case Cities->{
                Cities city = (Cities) packet.getModels().get(0);
                if (city == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        response_models.addAll(CitiesDao.INSTANCE.getAll());
                    return response_packet;
                }
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        response_models.addAll(CitiesDao.INSTANCE.get(city));
                    }
                    case Update -> {
                        CitiesDao.INSTANCE.update(city);
                    }
                    case Create -> {
                        CitiesDao.INSTANCE.save(city);
                    }
                    case Delete -> {
                        CitiesDao.INSTANCE.delete(city.getId());
                    }
                }
            }
        }
        return response_packet;
    }
}
