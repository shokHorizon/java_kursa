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
       Model successModel = null;

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
                                if (UsersDao.INSTANCE.update(user))
                                    response_models.add(null);
                        }
                        case Create -> {
                            if (AccessManager.hasRequiredAccess(token, 2))
                                if (UsersDao.INSTANCE.save(user))
                                    response_models.add(null);
                        }
                        case Delete -> {
                            if (AccessManager.hasRequiredAccess(token, 2))
                                if (UsersDao.INSTANCE.delete(user.getId()))
                                    response_models.add(null);
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
                    user.setHashedPassword(AccessManager.generateToken(user));
            }
            case Books->{
                if (packet.getModels() == null){
                    if (packet.getQueryMethod() == QueryMethod.Read) {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            response_models.addAll(BooksDao.INSTANCE.getAll());
                        else if (AccessManager.hasRequiredAccess(token, 1))
                            response_models.addAll(BooksDao.INSTANCE.getBySupplier(null,AccessManager.getId(token)));
                        else
                            response_models.addAll(BooksDao.INSTANCE.getByUser(AccessManager.getId(token)));
                        return response_packet;
                    }
                }
                Books book = null;
                BookRepr bookRepr = null;
                if (packet.getModels().get(0) instanceof Books books)
                    book = books;
                else if (packet.getModels().get(0) instanceof BookRepr booksRepr) {
                    bookRepr = booksRepr;
                }

                switch (packet.getQueryMethod()) {
                    case Read -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            response_models.addAll(BooksDao.INSTANCE.get(book));
                        else if (AccessManager.hasRequiredAccess(token, 1))
                            response_models.addAll(BooksDao.INSTANCE.getBySupplier(book, AccessManager.getId(token)));
                    }
                    case Update -> {
                        if (AccessManager.hasRequiredAccess(token, 1)) {
                            if (BooksDao.INSTANCE.update(book))
                                response_models.add(null);
                        }
                    }
                    case Create -> {
                        if (AccessManager.hasRequiredAccess(token, 0))
                            if (AccessManager.getAccessLevel(token) == 0){
                                if (BooksDao.INSTANCE.get(new Books(0, book.getTravel(), AccessManager.getId(token), -1)).size() > 0)
                                    return response_packet;
                                book.setUser(AccessManager.getId(token));
                            }
                            if (BooksDao.INSTANCE.save(book))
                                response_models.add(null);
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 0)) {
                            if (AccessManager.getAccessLevel(token) == 0){
                                if (BooksDao.INSTANCE.delete(bookRepr.getId()))
                                    response_models.add(null);
                            }
                            else if (BooksDao.INSTANCE.delete(book.getId())) {
                                response_models.add(null);
                            }
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
                            if (CountriesDao.INSTANCE.update(country))
                                response_models.add(null);
                    }
                    case Create -> {
                        if (AccessManager.hasRequiredAccess(token, 1))
                            if (CountriesDao.INSTANCE.save(country))
                                response_models.add(null);
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            if (CountriesDao.INSTANCE.delete(country.getId()))
                                response_models.add(null);
                    }
                }
            }
            case Travels->{
                if (packet.getModels() == null){
                    if (packet.getQueryMethod() == QueryMethod.Read)
                        if (AccessManager.getAccessLevel(token) == 2 )
                            response_models.addAll(TravelsDao.INSTANCE.getAll());
                        else if (AccessManager.getAccessLevel(token) == 0)
                            response_models.addAll(TravelsDao.INSTANCE.getRepr(null));
                        else if (AccessManager.getAccessLevel(token) == 1)
                            response_models.addAll(TravelsDao.INSTANCE.get(new Travels(AccessManager.getId(token))));
                    return response_packet;
                }
                Travels travel = null;
                TravelsRepr travelRepr = null;
                if (packet.getModels().get(0) instanceof Travels travels)
                    travel = travels;
                else if (packet.getModels().get(0) instanceof TravelsRepr travelsRepr)
                    travelRepr = travelsRepr;
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        if (AccessManager.getAccessLevel(token) == 2)
                            response_models.addAll(TravelsDao.INSTANCE.get(travel));
                        else if (AccessManager.getAccessLevel(token) == 0)
                            response_models.addAll(TravelsDao.INSTANCE.getRepr(travelRepr));
                        else if (AccessManager.getAccessLevel(token) == 1)
                            response_models.addAll(TravelsDao.INSTANCE.get(new Travels(AccessManager.getId(token))));
                    }
                    case Update -> {
                        if (AccessManager.getAccessLevel(token) >= 1
                        )
                            if(TravelsDao.INSTANCE.update(travel))
                                response_models.add(null);
                    }
                    case Create -> {
                        if (AccessManager.getAccessLevel(token) >= 2)
                            if(TravelsDao.INSTANCE.save(travel))
                                response_models.add(null);
                        else if (AccessManager.getAccessLevel(token) == 1) {
                            travel.setSupplier(AccessManager.getId(token));
                            if(TravelsDao.INSTANCE.save(travel))
                                response_models.add(null);
                        }
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 1))
                            if(TravelsDao.INSTANCE.delete(travel.getId()))
                                response_models.add(null);
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
                        if(response_models.addAll(TravelTypesDao.INSTANCE.get(travelType)))
                            response_models.add(null);
                    }
                    case Update -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            if(TravelTypesDao.INSTANCE.update(travelType))
                                response_models.add(null);
                    }
                    case Create -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            if(TravelTypesDao.INSTANCE.save(travelType))
                                response_models.add(null);
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            if(TravelTypesDao.INSTANCE.delete(travelType.getId()))
                                response_models.add(null);
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
                            if(CitiesDao.INSTANCE.update(city))
                                response_models.add(null);
                    }
                    case Create -> {
                        if (AccessManager.hasRequiredAccess(token, 1))
                            if(CitiesDao.INSTANCE.save(city))
                                response_models.add(null);
                    }
                    case Delete -> {
                        if (AccessManager.hasRequiredAccess(token, 2))
                            if(CitiesDao.INSTANCE.delete(city.getId()))
                                response_models.add(null);
                    }
                }
            }
        }
        return response_packet;
    }
}
