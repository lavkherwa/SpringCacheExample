package com.example.cacheImp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class CacheController {

	final TargetResource resourcePool;

	/*- Auto-wire using constructor 
	 * 
	 * Avoid field auto-wiring using @Autowired annotation
	 * and instead used constructor based auto-wiring
	 * 
	 */
	public CacheController(TargetResource resourcePool) {
		this.resourcePool = resourcePool;
	}

	@GetMapping(value = "/{id}")
	public String getMessage(@PathVariable("id") Long id) {
		return resourcePool.getResource(id);
	}

	@GetMapping
	public String getMessage2() {
		return resourcePool.getResource2();
	}

}
