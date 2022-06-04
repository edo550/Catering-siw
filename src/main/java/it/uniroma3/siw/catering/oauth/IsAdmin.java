package it.uniroma3.siw.catering.oauth;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@PreAuthorize("hasAuthority('READ') and hasAuthority('WRITE')")
@PreAuthorize("isAuthenticated()")
public @interface IsAdmin {
}