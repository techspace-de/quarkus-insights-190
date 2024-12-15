package de.techspace.quarkus.code.stage;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformerV2;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;

public class IdResponseTransformer implements ResponseDefinitionTransformerV2 {

    @Override
    public ResponseDefinition transform(ServeEvent serveEvent) {
        ResponseDefinition responseDefinition = serveEvent.getResponseDefinition();
        Parameters param = responseDefinition.getTransformerParameters();
        if (param != null) {
            param.put("id", "replaced");
        }
        return responseDefinition;
    }

    @Override
    public String getName() {
        return "id-transformer";
    }
}
