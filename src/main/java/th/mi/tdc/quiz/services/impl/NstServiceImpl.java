package th.mi.tdc.quiz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import th.mi.tdc.quiz.exception.ResourceNotFoundException;
import th.mi.tdc.quiz.entity.Nst;
import th.mi.tdc.quiz.repository.NstRepository;
import th.mi.tdc.quiz.services.NstService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class NstServiceImpl implements NstService {

    private NstRepository nstRepository;

    public NstServiceImpl(NstRepository nstRepository) {
        super();
        this.nstRepository = nstRepository;
    }

    @Override
    public Nst saveNst(Nst nst) {
        return nstRepository.save(nst);
    }

    @Override
    public List<Nst> getAllNst() {
        return nstRepository.findAll();
    }

    @Override
    public Page<Nst> getNstWithPagination(int offset, int pageSize){
        Page<Nst> nsts = nstRepository.findAll(PageRequest.of(offset, pageSize));
        return  nsts;
    }

    @Override
    public Page<Nst> getNstWithPageFilter(int offset, int pageSize, String first_name){
        Page<Nst> nsts = nstRepository.findByFirst_Name(first_name, PageRequest.of(offset, pageSize));
        return  nsts;
    }
//    public    Page<Nst> findByFirst_name(String first_name, PageRequest of) { return null;}

    @Override
    public  Optional<Nst> getNstById(Long id) { return nstRepository.findById(id); }

    @Override
    public  Nst updateNst(Nst nst, Long citizen_id) {
        Nst existingNst = nstRepository.findById(citizen_id).orElseThrow(
                () -> new ResourceNotFoundException("Nst", "ID", citizen_id));

//        existingNst.setQuiz_date(nst.getQuiz_date());
       return nstRepository.save(nst);
//        return existingNst;
    }

    @Override
    public void deleteNst(Long id) {
        nstRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Nst", "ID", id));
        nstRepository.deleteById(id);
    }

    @Override
    public Nst updateNstByFields(Long id, Map<String, Object> fields) {
        Optional<Nst> existingNst = nstRepository.findById(id);

        if (existingNst.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Nst.class,key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingNst.get(), value);
            });
            return nstRepository.save(existingNst.get());
        }
        return null;
    }

    @Override
    public  List<Nst>  getByUserName(String username) {
        return nstRepository.findByUserName(username);
    }
}