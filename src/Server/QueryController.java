package Server;


import DAO.*;
import Models.*;
import Protocols.Packet;
import Protocols.QueryMethod;
import Protocols.QueryModel;

import java.util.Optional;

public class QueryController {

   public static Packet<? extends Model> query_request(Packet<? extends Model> packet){
        switch (packet.getQueryModel()){
            case Users->{
                Users user = (Users) packet.getModels().get(0);
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        return new Packet<>(
                                QueryModel.Users,
                                null,
                                UsersDao.INSTANCE.getAll()
                        );
                    }
                    case Update -> {
                        user.setHashedPassword(user.hashPassword());
                        UsersDao.INSTANCE.update(user);
                    }
                    case Create -> {
                        user.setHashedPassword(user.hashPassword());
                        UsersDao.INSTANCE.save(user);
                    }
                    case Delete -> {
                        UsersDao.INSTANCE.delete(user.getId());
                    }
                    default -> {
                        Optional<Users> db_user = UsersDao.INSTANCE.get(user.getId());
                        if (user.hashPassword() == db_user.get().getHashedPassword()) {
                            user.setHashedPassword(0);
                        } else user.setAccessLevel(-1);
                    }
                }
            }
            case Books->{
                Books book = (Books) packet.getModels().get(0);
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        return new Packet<>(
                                QueryModel.Books,
                                null,
                                BooksDao.INSTANCE.getAll()
                        );
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
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        return new Packet<>(
                                QueryModel.Countries,
                                null,
                                CountriesDao.INSTANCE.getAll()
                        );
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
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        return new Packet<>(
                                QueryModel.Travels,
                                null,
                                TravelsDao.INSTANCE.getAll()
                        );
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
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        return new Packet<>(
                                QueryModel.TravelTypes,
                                null,
                                TravelTypesDao.INSTANCE.getAll()
                        );
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
                switch (packet.getQueryMethod()) {
                    case Read -> {
                        return new Packet<>(
                                QueryModel.Cities,
                                null,
                                CitiesDao.INSTANCE.getAll()
                        );
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
        return packet;
    }
}
