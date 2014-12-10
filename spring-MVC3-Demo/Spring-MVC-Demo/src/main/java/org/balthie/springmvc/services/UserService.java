package org.balthie.springmvc.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.balthie.springmvc.model.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * A sample user service to power the EXTJS application
 */
@Controller
@RequestMapping("/1")
//由于Spring MVC默认是Singleton的，所以会产生一个潜在的安全隐患。这个问题有两种解决办法：
//a) 在控制器中不使用实例变量
//b) 将控制器的作用域从单例改为原型
@Scope("prototype")
// a version # for this services
public class UserService
{
    // todo: demo only, don't use this Collection
    // For the demo we'll hard code a few users. However, for a real application this will
    // come from a db, most likely.
    private static Map<String, User> users = new HashMap<String, User>();
    static
    {
        User u1 = new User();
        u1.setId(UUID.randomUUID().toString());
        u1.setName("Ed");
        u1.setEmail("ed@sencha.com");
        users.put(u1.getId(), u1);
        
        User u2 = new User();
        u2.setId(UUID.randomUUID().toString());
        u2.setName("Tommy");
        u2.setEmail("tommy@sencha.com");
        users.put(u2.getId(), u2);
    }
    
    @RequestMapping(value = "/user/notice")
    public ModelAndView notice(HttpServletResponse response)
    {
        return new ModelAndView("home");
    }
    
    /**
     * Authenticate a user
     * @return the authenticated user
     * @request : http://localhost:8081/spring-mvc-demo/services/1/user/authenticate.json?email=balthie@126.com&password=123456
     * @response : {"user":{"id":"1","email":"balthie@126.com","name":"balthie"}}
     */
    @RequestMapping("/user/authenticate")
    public User authenticateUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam(value = "salt", required = false) String salt)
    {
        // TODO replace this with your real authentication code here.
        // return a new user object for the "authenticated" user
        User user = new User();
        user.setId("1");
        user.setName(email.split("@")[0]);
        user.setEmail(email);
        System.out.println(1/0);
        return user;
    }
    
    
    
    /**
     * return a list of users
     * @request: http://localhost:8081/spring-mvc-demo/services/1/user/list.json 
     * @response :
     *           {"userList":[{"id":"a7851aa9-707a-49eb-a311-8d53971d53f5","email":"tommy@sencha.com"
     *           ,"name"
     *           :"Tommy"},{"id":"429ee072-0bd7-40c7-b5a9-20822e362700","email":"ed@sencha.com"
     *           ,"name":"Ed"}]}
     */
    @RequestMapping("/user/list")
    public Collection<User> listUsers(HttpServletRequest request, HttpServletResponse response, Principal principal)
    {
        // TODO replace this with your real code here.
        return users.values();
    }
    
    /**
     * Update a user in the system
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Map updateUser(HttpServletRequest request, HttpServletResponse response, Principal principal, @RequestBody String json)
            throws Exception
    {
        // TODO replace this with your real code here.
        Collection<User> parsedUsers = parseUserJson(json);
        
        // Update all of the users (client is sending us array of users in json)
        if(parsedUsers != null)
        {
            for(User parsedUser : parsedUsers)
            {
                User localUser = users.get(parsedUser.getId());
                if(localUser == null)
                {
                    throw new RuntimeException("Invalid User");
                }
                
                // save changes to local user
                localUser.setName(parsedUser.getName());
                localUser.setEmail(parsedUser.getEmail());
            }
        }
        
        Map results = new HashMap();
        results.put("succes", true);
        return results;
    }
    
    /**
     * Parse an json packet of user(s)
     */
    private Collection<User> parseUserJson(String json) throws Exception
    {
        try
        {
            if(json.startsWith("[") && json.endsWith("]"))
            {
                // array of users
                ObjectMapper mapper = new ObjectMapper();
                TypeReference ref = new TypeReference<Collection<User>>() {};
                Collection<User> user = (Collection<User>) mapper.readValue(json, ref);
                return user;
            }
            else
            {
                // Single object
                ObjectMapper mapper = new ObjectMapper();
                Collection<User> users = new ArrayList<User>();
                users.add((User) mapper.readValue(json, User.class));
                return users;
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Invalid USER Json");
        }
    }

    /**
     * 
     */
    public UserService()
    {
        super();
        System.out.println("create UserService instance name = "+this.toString());
    }
    
    @ExceptionHandler({NumberFormatException.class, OutOfMemoryError.class})
    public Map<String, User> dealException(Exception ex)
    {
        System.out.println(" 捕获异常 "+ex.getMessage());
        
        // 模拟返回业务对象
        return users;
    }
}
