package adstech.vn.com.mobile.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import adstech.vn.com.mobile.security.UserPrincipal;
public class AuthenUtil {
	public static UserPrincipal getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
		return principal;
	}
}
