package Server;

import Protocols.Crypto;

import java.util.HashMap;
import java.util.Random;

public class AccessManager {
    private static final Random rand = new Random(System.currentTimeMillis());
    private static final HashMap<Integer, Integer> tokens = new HashMap<>();

    public static int generateToken(String login, int password, int accessLevel){
        int token;
        // Избегаем возможные коллизии путем пересоздания токена
        do {
            token = Crypto.hashInt(Crypto.hashString(login) + password + rand.nextInt(100));
        } while (tokens.containsKey(token));
        tokens.put(token, accessLevel);
        return token;
    }

    public static boolean hasRequiredAccess(int token, int accessLevel){
        return tokens.containsKey(token) && tokens.get(token) <= accessLevel;
    }

    public static int getAccessLevel(int token){
        if (tokens.containsKey(token))
            return tokens.get(token);
        return -1;
    }
}
