package improvement;

import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiConfigSetup{

    HashMap<String,String> header;
    HashMap<String,String> query;
    HashMap<String,String> param;
    RequestSpecification spec;

    protected ApiConfigSetup(){

        spec = given();
    }


    public void setAuth(String authId, String token){
        spec = given().auth().basic(authId, token);
    }

    public void setHeader(String s){
        header = new HashMap<String, String>();
        String[] h = s.split(",");
        for(int i=0;i<h.length;i++){
            String[] hl = h[i].split(":");
            header.put(hl[0], hl[1]);
        }
        for(String a:header.keySet()){
            System.out.println(a+" "+header.get(a));
        }
        spec.headers(header);
    }

    public void setQuery(String s){
        query = new HashMap<String, String>();
        String[] q = s.split("&");
        for(int i=0;i<q.length;i++){
            String[] ql = q[0].split("=");
            query.put(ql[0], ql[1]);
        }
        spec.queryParams(query);
    }

    public void setEndPoint(String uri){
        spec.baseUri(uri);
    }

    public void setBasePath(String path){
        spec.basePath(path);
    }

    public void setBody(String body){
        spec.body(body);
    }

    public void setPathParam(String s){
        param = new HashMap<String, String>();
        String[] p = s.split(",");
        for(int i=0;i<p.length;i++){
            String[] pp = p[i].split(":");
            param.put(pp[0], pp[1]);
        }
        for(String a:param.keySet()){
            System.out.println(a+" "+param.get(a));
        }
        spec.pathParams(param);
    }


    public RequestSpecification getSpec(){
        return spec;
    }
}
