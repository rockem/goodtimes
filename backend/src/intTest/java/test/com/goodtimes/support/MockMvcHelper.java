package test.com.goodtimes.support;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class MockMvcHelper {

    public static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private final MockMvc mockMvc;

    public MockMvcHelper(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public ResultActions postObjectToUrl(Object obj, String url) throws Exception {
        return this.mockMvc.perform(post(url)
                .contentType(CONTENT_TYPE)
                .content(new Gson().toJson(obj)));
    }
}
