package br.com.giba.cadmembros;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembroService {
	private final MembroRepository membroRepository;
}
