package juchoi.study.practice.repogitory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import juchoi.study.practice.repogitory.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  List<User> findByNameContaining(String name);

  List<User> findByIsActive(Boolean isActive);

  // native query 사용
  @Query(nativeQuery = true, value = "select * from users where create_at > :date")
  List<User> findUsersCreateAfter(@Param("date") LocalDateTime date);

  // 수정/삭제 쿼리
  @Modifying
  @Transactional
  @Query(nativeQuery = true, value = "update users set status = :status where id = :id")
  int updateStatus(@Param("id") Long id, @Param("status") Boolean status);

  // 페이징 지원
  Page<User> findByEmailContaining(String email, Pageable pageable);

  // Projection 사용
  @Query("SELECT u.name as name, u.email as email FROM User u WHERE u.id = :id")
  UserProjection findUserProjectionById(@Param("id") Long id);

  interface UserProjection {
    String getName();

    String getEmail();
  }
}
