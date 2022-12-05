package net.nwmissouri.springbootkafka.repository;

import net.nwmissouri.springbootkafka.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data,Long> {

}
