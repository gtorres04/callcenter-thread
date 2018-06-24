package com.gtorres.test.callcenter.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gtorres.test.callcenter.dto.Call;
import com.gtorres.test.callcenter.service.ICallsService;

@Service
public class CallsServiceImpl implements ICallsService {
	
	private List<Call> listCalls;
	
	/**
	 * @param listCalls
	 */
	@Autowired
	public CallsServiceImpl(List<Call> listCalls) {
		super();
		this.listCalls = listCalls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gtorres.test.callcenter.service.ICallsService#addCallReceived(com.gtorres
	 * .test.callcenter.dto.Call)
	 */
	@Override
	public void addCallReceived(Call call) {
		listCalls.add(call);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gtorres.test.callcenter.service.ICallsService#findById(java.lang.Long)
	 */
	@Override
	public Call findById(Long id) {
		return listCalls.stream().filter(call -> id.equals(call.getId())).findFirst().get();
	}

	/* (non-Javadoc)
	 * @see com.gtorres.test.callcenter.service.ICallsService#findByTimeFinished()
	 */
	@Override
	public List<Call> findByTimeFinished() {
		return this.listCalls.stream().filter(call -> null != call.getTimeFinished()).collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see com.gtorres.test.callcenter.service.ICallsService#findAll()
	 */
	@Override
	public List<Call> findAll() {
		return this.listCalls;
	}
}
