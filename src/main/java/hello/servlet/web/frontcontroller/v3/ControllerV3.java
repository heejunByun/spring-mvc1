package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // Servlet 기술이 안들어간 직접 만든 process
    ModelView process(Map<String, String> paramMap);

}
