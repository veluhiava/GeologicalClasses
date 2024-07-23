package com.example.GeologicalClasses;

import com.example.GeologicalClasses.entity.GeologicalClass;
import com.example.GeologicalClasses.entity.Section;
import com.example.GeologicalClasses.repository.GeologicalClassRepository;
import com.example.GeologicalClasses.repository.SectionRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class GeologicalClassesApplicationTests {

    @Test
    void contextLoads() {
    }
}

@DataJpaTest
@ActiveProfiles("test")
class SectionRepositoryTests {

    @Autowired
    private SectionRepository sectionRepository;

    @Test
    @DisplayName("Test saving a Section")
    void testSaveSection() {
        Section section = new Section();
        section.setName("Test Section");
        Section savedSection = sectionRepository.save(section);

        assertThat(savedSection).isNotNull();
        assertThat(savedSection.getId()).isNotNull();
        assertThat(savedSection.getName()).isEqualTo("Test Section");
    }

    @Test
    @DisplayName("Test finding a Section by Id")
    void testFindSectionById() {
        Section section = new Section();
        section.setName("Test Section");
        sectionRepository.save(section);

        Optional<Section> foundSection = sectionRepository.findById(section.getId());
        assertThat(foundSection).isPresent();
        assertThat(foundSection.get().getName()).isEqualTo("Test Section");
    }

    @Test
    @DisplayName("Test deleting a Section")
    void testDeleteSection() {
        Section section = new Section();
        section.setName("Test Section");
        sectionRepository.save(section);

        sectionRepository.deleteById(section.getId());

        Optional<Section> deletedSection = sectionRepository.findById(section.getId());
        assertThat(deletedSection).isNotPresent();
    }
}

@DataJpaTest
@ActiveProfiles("test")
class GeologicalClassRepositoryTests {

    @Autowired
    private GeologicalClassRepository geologicalClassRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Test
    @DisplayName("Test saving a GeologicalClass")
    void testSaveGeologicalClass() {
        Section section = new Section();
        section.setName("Test Section");
        sectionRepository.save(section);

        GeologicalClass geoClass = new GeologicalClass();
        geoClass.setName("Geo Class 1");
        geoClass.setCode("GC1");
        geoClass.setSection(section);

        GeologicalClass savedGeoClass = geologicalClassRepository.save(geoClass);

        assertThat(savedGeoClass).isNotNull();
        assertThat(savedGeoClass.getId()).isNotNull();
        assertThat(savedGeoClass.getName()).isEqualTo("Geo Class 1");
        assertThat(savedGeoClass.getCode()).isEqualTo("GC1");
        assertThat(savedGeoClass.getSection()).isEqualTo(section);
    }

    @Test
    @DisplayName("Test finding a GeologicalClass by Id")
    void testFindGeologicalClassById() {
        Section section = new Section();
        section.setName("Test Section");
        sectionRepository.save(section);

        GeologicalClass geoClass = new GeologicalClass();
        geoClass.setName("Geo Class 1");
        geoClass.setCode("GC1");
        geoClass.setSection(section);
        geologicalClassRepository.save(geoClass);

        Optional<GeologicalClass> foundGeoClass = geologicalClassRepository.findById(geoClass.getId());
        assertThat(foundGeoClass).isPresent();
        assertThat(foundGeoClass.get().getName()).isEqualTo("Geo Class 1");
        assertThat(foundGeoClass.get().getCode()).isEqualTo("GC1");
    }

    @Test
    void testDeleteGeologicalClass() {
        Section section = new Section();
        section.setName("Test Section");
        sectionRepository.save(section);

        GeologicalClass geoClass = new GeologicalClass();
        geoClass.setName("Geo Class 1");
        geoClass.setCode("GC1");
        geoClass.setSection(section);
        geologicalClassRepository.save(geoClass);

        geologicalClassRepository.deleteById(geoClass.getId());

        Optional<GeologicalClass> deletedGeoClass = geologicalClassRepository.findById(geoClass.getId());
        assertThat(deletedGeoClass).isNotPresent();
    }
}
