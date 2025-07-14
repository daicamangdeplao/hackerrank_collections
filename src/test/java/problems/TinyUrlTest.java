package problems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TinyUrlTest {

    private TinyUrl.TinyUrlService tinyUrlService;

    @BeforeEach
    void setUp() {
        tinyUrlService = new TinyUrl.TinyUrlService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getShortenUrl_should_return_character_a_in_first_time() {
        String shortenUrl = tinyUrlService.getShortenUrl("https://leetcode.com/problems/encode-and-decode-tinyurl/");
        assertThat(shortenUrl).isEqualTo("a");
    }

    @Test
    void getShortenUrl_should_return_the_same_shorten_URL_given_the_sameURL_during_multiple_times() {
        int requestedTime = 68;
        IntStream.range(0, requestedTime).forEach(i -> {
            tinyUrlService.getShortenUrl("https://leetcode.com/problems/encode-and-decode-tinyurl/");
        });
        String shortenUrl = tinyUrlService.getShortenUrl("https://leetcode.com/problems/encode-and-decode-tinyurl/");
        assertThat(shortenUrl).isEqualTo("a");
    }

    @Test
    void getShortenUrl_should_return_correct_characters_during_multiple_times() {
        int requestedTime = 68;
        String url = "https://leetcode.com/problems/encode-and-decode-tinyurl/";
        IntStream.range(0, requestedTime).forEach(i -> tinyUrlService.getShortenUrl(url + i));
        String shortenUrl = tinyUrlService.getShortenUrl(url + requestedTime);
        assertThat(shortenUrl).isEqualTo("bg");
    }

    @Test
    void getOriginalUrl_should_return_correct_URL() {
        int requestedTime = 68;
        String url = "https://leetcode.com/problems/encode-and-decode-tinyurl/";
        IntStream.range(0, requestedTime).forEach(i -> tinyUrlService.getShortenUrl(url + i));
        String shortenUrl = tinyUrlService.getShortenUrl(url + requestedTime);

        String originalUrl = tinyUrlService.getOriginalUrl("bg");
        assertThat(originalUrl).isEqualTo(url + requestedTime);
    }
}