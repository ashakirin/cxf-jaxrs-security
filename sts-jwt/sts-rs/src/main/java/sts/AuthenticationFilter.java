package sts;

import java.io.IOException;
import java.security.Principal;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.common.security.SimpleSecurityContext;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.impl.tl.ThreadLocalMessageContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.security.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class); 

    @Context
    private MessageContext messageContext;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		LOGGER.info("Postmatching Container request filter");
		Message message = PhaseInterceptorChain.getCurrentMessage();
		
		message.put(SecurityContext.class, new SimpleSecurityContext(new Principal() {
			
			@Override
			public String getName() {
				return "myTestUser";
			}
		}));
		
//		SecurityContext sc = (SecurityContext)messageContext.get(SecurityContext.class);
//		System.out.println(sc);
//		System.out.println(sc.getUserPrincipal().getName());
		
		System.out.println("*** Postmatching Container response filter ***");
	}

}
