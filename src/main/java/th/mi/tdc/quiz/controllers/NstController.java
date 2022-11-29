package th.mi.tdc.quiz.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.mi.tdc.quiz.entity.Nst;
import th.mi.tdc.quiz.payload.response.ApiResponse;
import th.mi.tdc.quiz.services.NstService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class NstController {

    private NstService nstService;

    public NstController(NstService nstService) {
        super();
        this.nstService = nstService;
    }
    // Create RequestVerify
    @PostMapping("/v1")
    public ResponseEntity<Nst> saveRequestVerify(@RequestBody Nst nst) {
        return new ResponseEntity<Nst>(nstService.saveNst(nst),
                HttpStatus.CREATED);
    }
    // Get All RequestVerify
    @GetMapping()
    public ApiResponse<List<Nst>> getAllNst() {
        List<Nst> allNst = nstService.getAllNst();

        return new ApiResponse<>(allNst.size(), allNst);
    }

    @GetMapping("/v1/pagination/{offset}/{pageSize}")
    private ApiResponse<Page<Nst>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Nst> nstWithPagination = nstService.getNstWithPagination(offset, pageSize);
        return new ApiResponse<>(nstWithPagination.getSize(), nstWithPagination);
    }

    @GetMapping("/v1/pagewithparams")
    private ApiResponse<Page<Nst>> getProductsWithPageFilter(@RequestParam int page, @RequestParam int limit ,@RequestParam String first_name) {
        Page<Nst> nstWithPaginationTest = nstService.getNstWithPageFilter(page, limit, first_name);
        return new ApiResponse<>(nstWithPaginationTest.getSize(), nstWithPaginationTest);
    }

    // Get by id
    @GetMapping("/v1/{citizen_id}")
    public Optional<Nst> getNstById(@PathVariable Long citizen_id) {
        return nstService.getNstById(citizen_id);
    }
    @PutMapping("/v1/{citizen_id}")
    public  ResponseEntity<Nst> updateNst(@PathVariable("citizen_id") Long id,
                                                              @RequestBody Nst nst) {

        return new  ResponseEntity<Nst>(nstService.updateNst(nst,
                id),HttpStatus.OK);
    }
    @DeleteMapping("/v1/{citizen_id}")
    public ResponseEntity<String> deleteRequestVerify(@PathVariable("citizen_id") Long id) {
        nstService.deleteNst(id);
        return  new ResponseEntity<String>(HttpStatus.OK);
    }
}