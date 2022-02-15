package ds.guang.oauth2.server;

import com.guang.dstoken.core.context.DsHolder;
import com.guang.dstoken.core.ds.DsManager;
import com.guang.dstoken.core.ds.DsUtil;
import com.guang.dstoken.core.util.result.DsBody;
import com.guang.dstoken.core.util.result.DsResult;
import com.guang.dstoken.ouath2.config.DsOAuth2Config;
import com.guang.dstoken.ouath2.logic.DsOAuth2Handle;
import com.guang.dstoken.ouath2.logic.DsOAuth2Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Sa-OAuth2 Server端 控制器
 * @author kong
 * 
 */
@RestController
public class DsOAuth2ServerController {


    /**
     *  处理所有OAuth相关请求
     */
	@RequestMapping("/oauth2/*")
	public Object request() {
		System.out.println("------- 进入请求: " + DsHolder.getRequest().getUrl());
		System.out.println(DsManager.getSaTokenContext().toString());
		return DsOAuth2Handle.serverRequest();
	}


	// 全局异常拦截  
	@ExceptionHandler
	public DsBody handlerException(Exception e) {
		e.printStackTrace(); 
		return DsBody.error(e.getMessage());
	}

    // Sa-OAuth2 定制化配置
    @Autowired
    public void setSaOAuth2Config(DsOAuth2Config cfg) {
        cfg.
                // 配置：未登录时返回的View
                        setNotLoginView(() -> {
                    String msg = "当前会话在SSO-Server端尚未登录，请先访问"
                            + "<a href='/oauth2/doLogin?name=sa&pwd=123456' target='_blank'> doLogin登录 </a>"
                            + "进行登录之后，刷新页面开始授权";
                    return msg;
                }).
                // 配置：登录处理函数
                        setDoLoginHandle((name, pwd) -> {
                    if("sa".equals(name) && "123456".equals(pwd)) {
                        DsUtil.login(10001);
                        return DsBody.ok();
                    }
                    return  DsBody.error("账号名或密码错误");
                }).
                // 配置：确认授权时返回的View
                        setConfirmView((clientId, scope) -> {
                    String msg = "<p>应用 " + clientId + " 请求授权：" + scope + "</p>"
                            + "<p>请确认：<a href='/oauth2/doConfirm?client_id=" + clientId + "&scope=" + scope + "' target='_blank'> 确认授权 </a></p>"
                            + "<p>确认之后刷新页面</p>";
                    return msg;
                })
        ;
    }
	
	// ---------- 开放相关资源接口： Client端根据 Access-Token ，置换相关资源 ------------ 
	
	// 获取Userinfo信息：昵称、头像、性别等等
	@RequestMapping("/oauth2/userinfo")
	public DsBody userinfo() {
		// 获取 Access-Token 对应的账号id 
		String accessToken = DsHolder.getRequest().getParamNotNull("access_token");
		Object loginId = DsOAuth2Util.getLoginIdByAccessToken(accessToken);
		System.out.println("-------- 此Access-Token对应的账号id: " + loginId);
		
		// 校验 Access-Token 是否具有权限: userinfo
		DsOAuth2Util.checkScope(accessToken, "userinfo");
		
		// 模拟账号信息 （真实环境需要查询数据库获取信息）
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("nickname", "shengzhang_");
		map.put("avatar", "http://xxx.com/1.jpg");
		map.put("age", "18");
		map.put("sex", "男");
		map.put("address", "山东省 青岛市 城阳区");
		return DsBody.data(map);
	}
	
}
