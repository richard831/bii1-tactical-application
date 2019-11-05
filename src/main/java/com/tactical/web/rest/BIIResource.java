package com.tactical.web.rest;

import com.tactical.domain.BII;
import com.tactical.repository.BIIRepository;
import com.tactical.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.tactical.domain.BII}.
 */
@RestController
@RequestMapping("/api")
public class BIIResource {

    private final Logger log = LoggerFactory.getLogger(BIIResource.class);

    private static final String ENTITY_NAME = "bII";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BIIRepository bIIRepository;

    public BIIResource(BIIRepository bIIRepository) {
        this.bIIRepository = bIIRepository;
    }

    /**
     * {@code POST  /biis} : Create a new bII.
     *
     * @param bII the bII to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bII, or with status {@code 400 (Bad Request)} if the bII has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/biis")
    public ResponseEntity<BII> createBII(@RequestBody BII bII) throws URISyntaxException {
        log.debug("REST request to save BII : {}", bII);
        if (bII.getId() != null) {
            throw new BadRequestAlertException("A new bII cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BII result = bIIRepository.save(bII);
        return ResponseEntity.created(new URI("/api/biis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /biis} : Updates an existing bII.
     *
     * @param bII the bII to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bII,
     * or with status {@code 400 (Bad Request)} if the bII is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bII couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/biis")
    public ResponseEntity<BII> updateBII(@RequestBody BII bII) throws URISyntaxException {
        log.debug("REST request to update BII : {}", bII);
        if (bII.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BII result = bIIRepository.save(bII);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bII.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /biis} : get all the bIIS.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bIIS in body.
     */
    @GetMapping("/biis")
    public List<BII> getAllBIIS() {
        log.debug("REST request to get all BIIS");
        return bIIRepository.findAll();
    }

    /**
     * {@code GET  /biis/:id} : get the "id" bII.
     *
     * @param id the id of the bII to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bII, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/biis/{id}")
    public ResponseEntity<BII> getBII(@PathVariable String id) {
        log.debug("REST request to get BII : {}", id);
        Optional<BII> bII = bIIRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bII);
    }

    /**
     * {@code DELETE  /biis/:id} : delete the "id" bII.
     *
     * @param id the id of the bII to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/biis/{id}")
    public ResponseEntity<Void> deleteBII(@PathVariable String id) {
        log.debug("REST request to delete BII : {}", id);
        bIIRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
