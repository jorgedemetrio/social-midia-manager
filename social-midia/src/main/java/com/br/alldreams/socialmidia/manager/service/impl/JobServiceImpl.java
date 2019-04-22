/**
 *
 */
package com.br.alldreams.socialmidia.manager.service.impl;

import static java.util.Objects.isNull;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.alldreams.socialmidia.conf.beans.DriversConfBeans;
import com.br.alldreams.socialmidia.conf.beans.SegurancaConfBeans;
import com.br.alldreams.socialmidia.manager.domain.Configuracao;
import com.br.alldreams.socialmidia.manager.repository.ComentarioRepository;
import com.br.alldreams.socialmidia.manager.repository.ConfiguracaoRepository;
import com.br.alldreams.socialmidia.manager.repository.HistoricoRepository;
import com.br.alldreams.socialmidia.manager.repository.TagRepository;
import com.br.alldreams.socialmidia.manager.repository.UsuarioRepository;
import com.br.alldreams.socialmidia.manager.service.JobService;
import com.br.alldreams.socialmidia.manager.utils.CriptografiaUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 19:56:11
 * @version 1.0
 */
@Slf4j
@Data
@Service
public class JobServiceImpl implements JobService {

	private final static HashMap<String, Thread> configuracoes = new HashMap<String, Thread>();

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private HistoricoRepository historicoRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private SegurancaConfBeans conf;

	@Autowired
	private CriptografiaUtils criptografia;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DriversConfBeans drivesConfig;

	@Autowired
	private ConfiguracaoRepository configuracaoRepository;

	public void removeEvento(String nome) {
		Thread thread = configuracoes.get(nome);
		thread.stop();
		configuracoes.remove(nome);
	}

	@Override
	public void startService() {
		Calendar cal = Calendar.getInstance();

		String hr = String.valueOf(cal.get(Calendar.HOUR_OF_DAY)).concat(String.valueOf(cal.get(Calendar.MINUTE)));

		List<Configuracao> list = configuracaoRepository.getConfiguracoesAtivas(hr);

		for (Configuracao configuracao : list) {
			if (isNull(configuracoes.get(configuracao.getDescricao()))) {
				Thread t = new Thread(new ControleRedeSocialThread(configuracao, this));
				configuracoes.put(configuracao.getDescricao(), t);
				t.start();

			}
		}
	}

}
