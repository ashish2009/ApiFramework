package improvement;

import io.restassured.response.Response;

public class RequestHandler extends ApiConfigSetup {

    private static Response response;
    private static Object parameter;
    private static int statusCode;



    /*
     * This method will post request based on header,query and path params
     */

    public Response returnGetApiResp() {

        System.out.println("Hitting api");
        try {
            response = spec.when()
                    .get()
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    /*
     * This method will post request based on header,query and path params
     */

    public int returnGetApiRespCode() {

        System.out.println("Hitting api");
        try {
            statusCode = spec.when()
                    .get()
                    .getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusCode;
    }

    /*
     * This method will post request based on header,query and path params and
     * payload of request
     */

    public Response returnPostApiResp() {
        System.out.println("Hitting api");
        try {
            response = spec.when()
                    .post()
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public int returnPostApiRespCode() {

        System.out.println("Hitting api");
        try {
            statusCode = spec.when()
                    .post()
                    .getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusCode;
    }


    public Object returnParamsFromResp(String extractedPath) {

        System.out.println("Hitting api");
        try {
            parameter = spec.when()
                    .post()
                    .then()
                    .extract()
                    .path(extractedPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parameter;
    }
}
