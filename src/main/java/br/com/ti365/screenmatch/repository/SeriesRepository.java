package br.com.ti365.screenmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ti365.screenmatch.model.Series;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long>{

}
