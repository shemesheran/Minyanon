package minyanon.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {
		
	@Autowired
	AddressRESTToEntityService addressService;
	
//	@RequestMapping(method=RequestMethod.GET)
//	public List<AddressREST> getAll() throws Exception {
//		return addressService.getAll(new AddressREST(null));
//	}
	
//	@RequestMapping(value="/{addressName}", method=RequestMethod.PUT)
//	public void createAddress(@PathVariable String addressName) {
//		addressService.addNewAddress(addressName);
//	}
	
//	@RequestMapping(value="/{addressName}", method=RequestMethod.DELETE)
//	public void deleteAddress(@PathVariable String addressName) {
//		addressService.deleteAddress(addressName);
//	}

}
