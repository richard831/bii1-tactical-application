package com.tactical.web.rest;

import com.tactical.Bii1TacticalApplicationApp;
import com.tactical.domain.BII;
import com.tactical.repository.BIIRepository;
import com.tactical.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.util.List;

import static com.tactical.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BIIResource} REST controller.
 */
@SpringBootTest(classes = Bii1TacticalApplicationApp.class)
public class BIIResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BII_ID = "AAAAAAAAAA";
    private static final String UPDATED_BII_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_DETECTION_TIMESTAMP = 1L;
    private static final Long UPDATED_DETECTION_TIMESTAMP = 2L;

    private static final String DEFAULT_SOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DETECTION_SYSTEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DETECTION_SYSTEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DETECTED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_DETECTED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_DETECTION_CONTEXT = "AAAAAAAAAA";
    private static final String UPDATED_DETECTION_CONTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_ETC = "AAAAAAAAAA";
    private static final String UPDATED_ETC = "BBBBBBBBBB";

    private static final String DEFAULT_ETCETC = "AAAAAAAAAA";
    private static final String UPDATED_ETCETC = "BBBBBBBBBB";

    @Autowired
    private BIIRepository bIIRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restBIIMockMvc;

    private BII bII;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BIIResource bIIResource = new BIIResource(bIIRepository);
        this.restBIIMockMvc = MockMvcBuilders.standaloneSetup(bIIResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BII createEntity() {
        BII bII = new BII()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .biiId(DEFAULT_BII_ID)
            .detectionTimestamp(DEFAULT_DETECTION_TIMESTAMP)
            .sourceId(DEFAULT_SOURCE_ID)
            .detectionSystemName(DEFAULT_DETECTION_SYSTEM_NAME)
            .detectedValue(DEFAULT_DETECTED_VALUE)
            .detectionContext(DEFAULT_DETECTION_CONTEXT)
            .etc(DEFAULT_ETC)
            .etcetc(DEFAULT_ETCETC);
        return bII;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BII createUpdatedEntity() {
        BII bII = new BII()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .biiId(UPDATED_BII_ID)
            .detectionTimestamp(UPDATED_DETECTION_TIMESTAMP)
            .sourceId(UPDATED_SOURCE_ID)
            .detectionSystemName(UPDATED_DETECTION_SYSTEM_NAME)
            .detectedValue(UPDATED_DETECTED_VALUE)
            .detectionContext(UPDATED_DETECTION_CONTEXT)
            .etc(UPDATED_ETC)
            .etcetc(UPDATED_ETCETC);
        return bII;
    }

    @BeforeEach
    public void initTest() {
        bIIRepository.deleteAll();
        bII = createEntity();
    }

    @Test
    public void createBII() throws Exception {
        int databaseSizeBeforeCreate = bIIRepository.findAll().size();

        // Create the BII
        restBIIMockMvc.perform(post("/api/biis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bII)))
            .andExpect(status().isCreated());

        // Validate the BII in the database
        List<BII> bIIList = bIIRepository.findAll();
        assertThat(bIIList).hasSize(databaseSizeBeforeCreate + 1);
        BII testBII = bIIList.get(bIIList.size() - 1);
        assertThat(testBII.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBII.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBII.getBiiId()).isEqualTo(DEFAULT_BII_ID);
        assertThat(testBII.getDetectionTimestamp()).isEqualTo(DEFAULT_DETECTION_TIMESTAMP);
        assertThat(testBII.getSourceId()).isEqualTo(DEFAULT_SOURCE_ID);
        assertThat(testBII.getDetectionSystemName()).isEqualTo(DEFAULT_DETECTION_SYSTEM_NAME);
        assertThat(testBII.getDetectedValue()).isEqualTo(DEFAULT_DETECTED_VALUE);
        assertThat(testBII.getDetectionContext()).isEqualTo(DEFAULT_DETECTION_CONTEXT);
        assertThat(testBII.getEtc()).isEqualTo(DEFAULT_ETC);
        assertThat(testBII.getEtcetc()).isEqualTo(DEFAULT_ETCETC);
    }

    @Test
    public void createBIIWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bIIRepository.findAll().size();

        // Create the BII with an existing ID
        bII.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restBIIMockMvc.perform(post("/api/biis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bII)))
            .andExpect(status().isBadRequest());

        // Validate the BII in the database
        List<BII> bIIList = bIIRepository.findAll();
        assertThat(bIIList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllBIIS() throws Exception {
        // Initialize the database
        bIIRepository.save(bII);

        // Get all the bIIList
        restBIIMockMvc.perform(get("/api/biis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bII.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].biiId").value(hasItem(DEFAULT_BII_ID)))
            .andExpect(jsonPath("$.[*].detectionTimestamp").value(hasItem(DEFAULT_DETECTION_TIMESTAMP.intValue())))
            .andExpect(jsonPath("$.[*].sourceId").value(hasItem(DEFAULT_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].detectionSystemName").value(hasItem(DEFAULT_DETECTION_SYSTEM_NAME)))
            .andExpect(jsonPath("$.[*].detectedValue").value(hasItem(DEFAULT_DETECTED_VALUE)))
            .andExpect(jsonPath("$.[*].detectionContext").value(hasItem(DEFAULT_DETECTION_CONTEXT)))
            .andExpect(jsonPath("$.[*].etc").value(hasItem(DEFAULT_ETC)))
            .andExpect(jsonPath("$.[*].etcetc").value(hasItem(DEFAULT_ETCETC)));
    }
    
    @Test
    public void getBII() throws Exception {
        // Initialize the database
        bIIRepository.save(bII);

        // Get the bII
        restBIIMockMvc.perform(get("/api/biis/{id}", bII.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bII.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.biiId").value(DEFAULT_BII_ID))
            .andExpect(jsonPath("$.detectionTimestamp").value(DEFAULT_DETECTION_TIMESTAMP.intValue()))
            .andExpect(jsonPath("$.sourceId").value(DEFAULT_SOURCE_ID))
            .andExpect(jsonPath("$.detectionSystemName").value(DEFAULT_DETECTION_SYSTEM_NAME))
            .andExpect(jsonPath("$.detectedValue").value(DEFAULT_DETECTED_VALUE))
            .andExpect(jsonPath("$.detectionContext").value(DEFAULT_DETECTION_CONTEXT))
            .andExpect(jsonPath("$.etc").value(DEFAULT_ETC))
            .andExpect(jsonPath("$.etcetc").value(DEFAULT_ETCETC));
    }

    @Test
    public void getNonExistingBII() throws Exception {
        // Get the bII
        restBIIMockMvc.perform(get("/api/biis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBII() throws Exception {
        // Initialize the database
        bIIRepository.save(bII);

        int databaseSizeBeforeUpdate = bIIRepository.findAll().size();

        // Update the bII
        BII updatedBII = bIIRepository.findById(bII.getId()).get();
        updatedBII
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .biiId(UPDATED_BII_ID)
            .detectionTimestamp(UPDATED_DETECTION_TIMESTAMP)
            .sourceId(UPDATED_SOURCE_ID)
            .detectionSystemName(UPDATED_DETECTION_SYSTEM_NAME)
            .detectedValue(UPDATED_DETECTED_VALUE)
            .detectionContext(UPDATED_DETECTION_CONTEXT)
            .etc(UPDATED_ETC)
            .etcetc(UPDATED_ETCETC);

        restBIIMockMvc.perform(put("/api/biis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBII)))
            .andExpect(status().isOk());

        // Validate the BII in the database
        List<BII> bIIList = bIIRepository.findAll();
        assertThat(bIIList).hasSize(databaseSizeBeforeUpdate);
        BII testBII = bIIList.get(bIIList.size() - 1);
        assertThat(testBII.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBII.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBII.getBiiId()).isEqualTo(UPDATED_BII_ID);
        assertThat(testBII.getDetectionTimestamp()).isEqualTo(UPDATED_DETECTION_TIMESTAMP);
        assertThat(testBII.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testBII.getDetectionSystemName()).isEqualTo(UPDATED_DETECTION_SYSTEM_NAME);
        assertThat(testBII.getDetectedValue()).isEqualTo(UPDATED_DETECTED_VALUE);
        assertThat(testBII.getDetectionContext()).isEqualTo(UPDATED_DETECTION_CONTEXT);
        assertThat(testBII.getEtc()).isEqualTo(UPDATED_ETC);
        assertThat(testBII.getEtcetc()).isEqualTo(UPDATED_ETCETC);
    }

    @Test
    public void updateNonExistingBII() throws Exception {
        int databaseSizeBeforeUpdate = bIIRepository.findAll().size();

        // Create the BII

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBIIMockMvc.perform(put("/api/biis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bII)))
            .andExpect(status().isBadRequest());

        // Validate the BII in the database
        List<BII> bIIList = bIIRepository.findAll();
        assertThat(bIIList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBII() throws Exception {
        // Initialize the database
        bIIRepository.save(bII);

        int databaseSizeBeforeDelete = bIIRepository.findAll().size();

        // Delete the bII
        restBIIMockMvc.perform(delete("/api/biis/{id}", bII.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BII> bIIList = bIIRepository.findAll();
        assertThat(bIIList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BII.class);
        BII bII1 = new BII();
        bII1.setId("id1");
        BII bII2 = new BII();
        bII2.setId(bII1.getId());
        assertThat(bII1).isEqualTo(bII2);
        bII2.setId("id2");
        assertThat(bII1).isNotEqualTo(bII2);
        bII1.setId(null);
        assertThat(bII1).isNotEqualTo(bII2);
    }
}
