package com.eng.stream.hackathon.bookmark.audit;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

/*
 * public class AuditorAwareImpl implements AuditorAware<String> {
 * 
 * @Autowired private HttpServletRequest request;
 * 
 * @Override public Optional<String> getCurrentAuditor() {
 * request.getUserPrincipal().getName(); return
 * Optional.of("bookmark.application"); } }
 */

