package init.service;

import java.util.List;

import org.springframework.stereotype.Service;

import init.model.Tema;


public interface TemaService {
	List<Tema> findAll();
}
