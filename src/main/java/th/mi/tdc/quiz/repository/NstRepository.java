package th.mi.tdc.quiz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import th.mi.tdc.quiz.entity.Nst;

import java.util.List;

@Repository
public interface NstRepository extends JpaRepository<Nst, Long> {

  @Query(value = "SELECT n FROM nst n WHERE n.first_name like %:first_name%", nativeQuery = true)
  Page<Nst> findByFirst_Name(String first_name, Pageable pageable);

  @Query(value = "SELECT * FROM nst n WHERE n.username like %:username% ", nativeQuery = true)
  List<Nst> findByUserName(String username);

  Boolean existsByUsername(String username);

  Nst findByUsername(String citizen_id);

}
