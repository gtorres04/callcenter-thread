package com.gtorres.test.callcenter.service;

import java.util.List;

import com.gtorres.test.callcenter.dto.Call;

public interface ICallsService {
	/**
	 * Se adiciona a la lista de llamada recibidas
	 * 
	 * @param call
	 *            llamada recibida
	 */
	public void addCallReceived(Call call);

	/**
	 * Buscar llamada por el identificador de esta
	 * 
	 * @param id
	 *            identificador de la llamada
	 * @return
	 */
	public Call findById(Long id);

	/**
	 * Busca las llamadas finalizadas
	 * 
	 * @return lista de llamadas finalizadas.
	 */
	public List<Call> findByTimeFinished();

	/**
	 * Retorna todas las llamadas que se recibieron
	 * 
	 * @return listado de llamadas recibidas
	 */
	public List<Call> findAll();
}
