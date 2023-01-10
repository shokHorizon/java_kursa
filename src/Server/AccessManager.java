package Server;

import Models.Users;
import Protocols.Crypto;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Random;

public class AccessManager {
    private static final Random rand = new Random(System.currentTimeMillis());
    private static final HashMap<Integer, int[]> tokens = new HashMap<>();

    public static int generateToken(Users user){
        int token;
        // Избегаем возможные коллизии путем пересоздания токена
        do {
            token = Crypto.hashInt(Crypto.hashString(user.getLogin()) + user.getHashedPassword() + rand.nextInt(100));
        } while (tokens.containsKey(token));
        tokens.put(token, new int[]{user.getId(), user.getAccessLevel()});
        return token;
    }

    public static boolean hasRequiredAccess(int token, int accessLevel){
        return tokens.containsKey(token) && tokens.get(token)[1] >= accessLevel;
    }

    public static int getAccessLevel(int token){
        if (tokens.containsKey(token))
            return tokens.get(token)[1];
        return -1;
    }

    public static int getId(int token){
        if (tokens.containsKey(token))
            return tokens.get(token)[0];
        return 0;
    }
}
