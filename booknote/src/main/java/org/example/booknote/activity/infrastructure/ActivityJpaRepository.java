package org.example.booknote.activity.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityJpaRepository extends JpaRepository<ActivityEntity,Long> {
}
