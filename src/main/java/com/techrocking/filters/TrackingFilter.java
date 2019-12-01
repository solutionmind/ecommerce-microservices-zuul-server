package com.techrocking.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;


@Component
public class TrackingFilter extends ZuulFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);
	
	@Autowired
	private FilterUtil filterUtil;

	@Override
	public boolean shouldFilter() {
		return FilterUtil.SHOULD_FILTER;
	}

	@Override
	public String filterType() {
		return FilterUtil.FILTER_TYPE_PRE;
	}

	@Override
	public int filterOrder() {
		return FilterUtil.FILTER_ORDER;
	}
	
	@Override
	public Object run() throws ZuulException {
		filterUtil.setTransactionId();
		logger.info("transaction id created : " + filterUtil.getTransactionId());
		return null;
	}
	
}
