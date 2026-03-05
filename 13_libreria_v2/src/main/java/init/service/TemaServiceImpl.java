package init.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.model.Tema;
import init.repository.TemaRepository;
@Service
public class TemaServiceImpl implements TemaService {
	
	@Autowired
	TemaRepository temaRepository;
	@Override
	public List<Tema> findAll() {
		return temaRepository.findAll();
	}

}
