package problems;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <a href="https://leetcode.com/problems/encode-and-decode-tinyurl/description/?envType=problem-list-v2&envId=hash-table">...</a>
 * https://leetcode.com/discuss/post/124658/design-url-shortening-service-like-tinyu-047o/
 * */
public class TinyUrl {
    public static class TinyUrlService {
        private static final String[] BASE62 = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };

        private AtomicLong currentId = new AtomicLong(-1);
        private Map<Key, ShortenUrls> map = new HashMap<>();

        public String getShortenUrl(String originalUrl) {
            long currentId = this.currentId.get();
            if (map.containsKey(new Key(currentId, originalUrl))) {
                return map.get(new Key(currentId, originalUrl)).shortenUrl;
            }

            long id = getNextId();
            String encode = encode(id);
            map.put(new Key(id, originalUrl), new ShortenUrls(id, encode, originalUrl));
            return encode;
        }

        public String getOriginalUrl(String shortenUrl) {
            return decode(shortenUrl);
        }

        private long getNextId() {
            return currentId.incrementAndGet();
        }

        private String encode(long id) {
            String shortenUrl = "";
            do {
                shortenUrl = String.join("", shortenUrl, BASE62[(int) (id % 62)]);
                id /= 62;
            } while (id > 0);

            List<String> shortenUrlAsList = Arrays.asList(shortenUrl.split(""));
            Collections.reverse(shortenUrlAsList);
            return String.join("", shortenUrlAsList);
        }

        private String decode(String shortenUrl) {
            long id = 0;
            String[] shortenUrlAsList = shortenUrl.split("");
            for (String s : shortenUrlAsList) {
                if (s.matches("[a-z]")) {
                    id = id * 62 + s.toCharArray()[0] - 'a';
                }
                if (s.matches("[A-Z]")) {
                    id = id * 62 + s.toCharArray()[0] - 'A' + 26;
                }
                if (s.matches("[0-9]")) {
                    id = id * 62 + s.toCharArray()[0] - '0' + 52;
                }
            }
            long finalId = id;
            Optional<Key> any = map.keySet().stream()
                    .filter(key -> key.id == finalId)
                    .findAny();
            return any.map(key -> map.get(key).originalUrl).orElse("");
        }
    }
    public record ShortenUrls(
            long id,
            String shortenUrl,
            String originalUrl
    ) {}

    public record Key(
            long id,
            String originalUrl
    ) {}
}
