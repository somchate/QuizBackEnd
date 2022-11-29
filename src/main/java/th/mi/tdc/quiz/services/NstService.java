package th.mi.tdc.quiz.services;

import org.springframework.data.domain.Page;
import th.mi.tdc.quiz.entity.Nst;

import java.util.List;
import java.util.Optional;

public interface NstService {

    Nst saveNst(Nst nst);
    List<Nst> getAllNst();
    Page<Nst> getNstWithPagination(int offset, int pageSize);
    Page<Nst> getNstWithPageFilter(int offset, int pageSize, String first_name);
    Optional<Nst> getNstById(Long id);
    Nst updateNst(Nst nst, Long id);
    void deleteNst(Long id);
}
