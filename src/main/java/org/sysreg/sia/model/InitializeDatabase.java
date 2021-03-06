package org.sysreg.sia.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.util.DigestUtils;
import org.sysreg.sia.daos.*;
import org.sysreg.sia.model.actuator.Actuator;
import org.sysreg.sia.model.actuator.BasicActuator;
import org.sysreg.sia.model.sensor.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class InitializeDatabase {
    private static final Logger log = LogManager.getLogger(InitializeDatabase.class.getName());

    private ApplicationContext context;
    private ServerDAO serverDAO;
    private BoardDAO boardDAO;
    private ActuatorDAO actuatorDAO;
    private SensorDAO sensorDAO;
    private UseDAO useDAO;
    private EnclosureDAO enclosureDAO;
    private ParcelDAO parcelDAO;
    private FieldDAO fieldDAO;
    private TownDAO townDAO;
    private UserDAO userDAO;
    private AuthorityDAO authorityDAO;
    private VarietyDAO varietyDAO;
    private PropertiesFactoryBean propertiesFactoryBean;

    public static void main(String[] args) {
        log.info("Initializing population");
        InitializeDatabase populate = new InitializeDatabase();
        log.info("Droping and creating Database");
        populate.dropAndCreate();
        log.info("Populating Provinces, Regions and Towns");
        populate.loadTowns();
        log.info("Populating users and authorities");
        populate.loadUsersAndAuthorities();
        log.info("Populating SIGPAC uses");
        populate.loadUses();
        log.info("Populating orange varieties");
        populate.loadVarieties();
        log.info("Populating data for tests");
        populate.loadTestData();
        log.info("Populating sample data");
        populate.loadSampleData();
        log.info("Finished");

    }

    public InitializeDatabase() {
        //Load context
        context = new ClassPathXmlApplicationContext(
                "file:src/main/webapp/WEB-INF/applicationContext.xml");

        authorityDAO = context.getBean(AuthorityDAO.class);
        userDAO = context.getBean(UserDAO.class);
        townDAO = context.getBean(TownDAO.class);
        fieldDAO = context.getBean(FieldDAO.class);
        parcelDAO = context.getBean(ParcelDAO.class);
        enclosureDAO = context.getBean(EnclosureDAO.class);
        useDAO = context.getBean(UseDAO.class);
        sensorDAO = context.getBean(SensorDAO.class);
        actuatorDAO = context.getBean(ActuatorDAO.class);
        boardDAO = context.getBean(BoardDAO.class);
        serverDAO = context.getBean(ServerDAO.class);
        varietyDAO = context.getBean(VarietyDAO.class);
        propertiesFactoryBean = context.getBean(PropertiesFactoryBean.class);

    }

    public void dropAndCreate(){
        try {
                // Load the Hibernate configuration
                LocalContainerEntityManagerFactoryBean factoryBean = context.getBean(LocalContainerEntityManagerFactoryBean.class);
                Ejb3Configuration cfg = new Ejb3Configuration();
                Ejb3Configuration configured = cfg.configure(factoryBean.getPersistenceUnitInfo(), factoryBean.getJpaPropertyMap());
                // Set the datasource connection
                Connection connection = factoryBean.getDataSource().getConnection();
                SchemaExport schemaExport = new SchemaExport(configured.getHibernateConfiguration(), connection);
                // Drop and create schema
                schemaExport.create(true,true);
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public void loadTowns() {
        // Configure Eclipse to add automatically escapes
        // Go to: Preferences/Java/Editor/Typing/ "Escape text when pasting into a string literal"

        // Open a transaction
        EntityManagerFactory factory = (EntityManagerFactory) context.getBean("entityManagerFactory");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        // Insert PROVINCIAS
        Query queryProvinces = entityManager
                .createNativeQuery("INSERT INTO \"public\".\"provinces\" VALUES ('12', 'Castelló / Castellón');"
                        + "INSERT INTO \"public\".\"provinces\" VALUES ('3', 'Alacant / Alicante');"
                        + "INSERT INTO \"public\".\"provinces\" VALUES ('46', 'València');");
        queryProvinces.executeUpdate();

        // Insert COMARCAS
        Query queryRegions = entityManager
                .createNativeQuery("INSERT INTO \"public\".\"regions\" VALUES ('2', 'El Baix Vinalopó', '3');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('3', 'El Comtat', '3');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('5', 'La Marina Alta', '3');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('6', 'La Marina Baixa', '3');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('7', 'L''Alacantí', '3');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('8', 'L''Alcoià', '3');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('10', 'El Alto Mijares', '12');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('11', 'El Alto Palancia', '12');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('12', 'El Baix Maestrat', '12');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('13', 'Els Ports', '12');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('14', 'La Plana Alta', '12');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('15', 'La Plana Baixa', '12');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('16', 'L''Alcalatén', '12');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('17', 'L''Alt Maestrat', '12');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('18', 'El Camp de Morvedre', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('19', 'El Camp de Túria', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('20', 'El Rincón de Ademuz', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('21', 'El Valle de Cofrentes-Ayora', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('22', 'La Canal de Navarrés', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('23', 'La Costera', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('24', 'La Hoya de Buñol', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('25', 'La Plana de Utiel-Requena', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('26', 'La Ribera Alta', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('27', 'La Ribera Baixa', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('28', 'La Safor', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('29', 'La Vall d''Albaida', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('30', 'L''Horta Nord', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('31', 'L''Horta Oest', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('32', 'L''Horta Sud', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('33', 'Los Serranos', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('34', 'València', '46');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('9', 'L''Alt Vinalopó / Alto Vinalopó', '3');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('4', 'El Vinalopó Mitjà / El Vinalopó Medio', '3');"
                        + "INSERT INTO \"public\".\"regions\" VALUES ('1', 'El Baix Segura / La Vega Baja', '3');");
        queryRegions.executeUpdate();
        // Insert MUNICIPIOS
        Query queryTowns = entityManager
                .createNativeQuery("INSERT INTO \"public\".\"towns\" VALUES ('46250', 'València', '34');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46018', 'Alcublas', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46036', 'Alpuente', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46038', 'Andilla', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46041', 'Aras de los Olmos', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46050', 'Benagéber', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46076', 'Bugarra', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46079', 'Calles', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46106', 'Chelva', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46112', 'Chulilla', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46114', 'Domeño', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46133', 'Gestalgar', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46141', 'Higueruelas', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46149', 'Losa del Obispo', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46191', 'Pedralba', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46234', 'Sot de Chera', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46241', 'Titaguas', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46247', 'Tuéjar', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46258', 'Villar del Arzobispo', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46262', 'Yesa, La', '33');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46003', 'Atzeneta d''Albaida', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46004', 'Agullent', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46006', 'Albaida', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46027', 'Alfarrasí', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46042', 'Aielo de Malferit', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46043', 'Aielo de Rugat', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46047', 'Bèlgida', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46049', 'Bellús', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46056', 'Beniatjar', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46057', 'Benicolet', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46062', 'Benigánim', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46068', 'Benissoda', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46069', 'Benisuera', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46072', 'Bocairent', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46075', 'Bufali', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46086', 'Carrícola', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46090', 'Castelló de Rugat', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46104', 'Quatretonda', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46124', 'Fontanars dels Alforins', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46138', 'Guadasequies', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46150', 'Llutxent', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46173', 'Montaverner', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46175', 'Montitxelvo / Montichelvo', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46183', 'Olleria, l''', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46184', 'Ontinyent', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46185', 'Otos', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46189', 'Palomar, el', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46196', 'Pinet', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46200', 'Pobla del Duc, la', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46210', 'Ráfol de Salem', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46219', 'Rugat', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46221', 'Salem', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46226', 'Sempere', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46240', 'Terrateig', '29');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46002', 'Ador', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46023', 'Alfauir', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46033', 'Almiserà', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46034', 'Almoines', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46037', 'Alqueria de la Comtessa, l''', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46046', 'Barx', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46048', 'Bellreguard', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46055', 'Beniarjó', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46059', 'Benifairó de la Valldigna', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46061', 'Beniflá', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46066', 'Benirredrà', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46091', 'Castellonet de la Conquesta', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46113', 'Daimús', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46127', 'Font d''En Carròs, la', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46131', 'Gandia', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46140', 'Guardamar de la Safor', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46143', 'Xeraco', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46146', 'Xeresa', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46153', 'Llocnou de Sant Jeroni', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46168', 'Miramar', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46181', 'Oliva', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46187', 'Palma de Gandía', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46188', 'Palmera', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46195', 'Piles', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46198', 'Potríes', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46208', 'Rafelcofer', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46211', 'Real de Gandía', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46218', 'Rótova', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46231', 'Simat de la Valldigna', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46238', 'Tavernes de la Valldigna', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46255', 'Villalonga', '28');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46008', 'Albalat de la Ribera', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46035', 'Almussafes', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46098', 'Corbera', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46105', 'Cullera', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46123', 'Favara', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46125', 'Fortaleny', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46155', 'Llaurí', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46197', 'Polinyà de Xúquer', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46215', 'Riola', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46233', 'Sollana', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46235', 'Sueca', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46904', 'Benicull de Xúquer', '27');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46011', 'Alberic', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46016', 'Alcàntera de Xúquer', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46017', 'Alzira', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46019', 'Alcúdia, l''', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46026', 'Alfarp', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46029', 'Algemesí', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46031', 'Alginet', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46040', 'Antella', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46053', 'Beneixida', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46060', 'Benifaió', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46063', 'Benimodo', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46064', 'Benimuslem', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46083', 'Carcaixent', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46084', 'Càrcer', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46085', 'Carlet', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46093', 'Catadau', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46100', 'Cotes', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46119', 'Ènova, l''', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46130', 'Gavarda', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46139', 'Guadassuar', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46156', 'Llombai', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46160', 'Manuel', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46162', 'Massalavés', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46172', 'Montserrat', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46176', 'Montroy', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46203', 'Pobla Llarga, la', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46209', 'Rafelguaraf', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46212', 'Real', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46222', 'Sant Joanet', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46225', 'Sellent', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46227', 'Senyera', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46236', 'Sumacàrcer', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46246', 'Tous', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46248', 'Turís', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46257', 'Villanueva de Castellón', '26');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46080', 'Camporrobles', '25');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46095', 'Caudete de las Fuentes', '25');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46108', 'Chera', '25');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46129', 'Fuenterrobles', '25');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46213', 'Requena', '25');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46232', 'Sinarcas', '25');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46249', 'Utiel', '25');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46254', 'Venta del Moro', '25');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46259', 'Villargordo del Cabriel', '25');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12002', 'Aín', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12006', 'Alcudia de Veo', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12007', 'Alfondeguilla', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12011', 'Almenara', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12016', 'Artana', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12021', 'Betxí', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12032', 'Borriana / Burriana', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12053', 'Chilches / Xilxes', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12057', 'Eslida', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12074', 'Llosa, la', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12077', 'Moncofa', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12082', 'Nules', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12084', 'Onda', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12095', 'Ribesalbes', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12108', 'Suera / Sueras', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12109', 'Tales', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12126', 'Vall d''Uixó, la', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12135', 'Vila-real', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12136', 'Vilavella, la', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12901', 'Alquerías del Niño Perdido', '15');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12009', 'Almassora / Almazora', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12028', 'Benicàssim / Benicasim', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12029', 'Benlloch', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12031', 'Borriol', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12033', 'Cabanes', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12040', 'Castelló de la Plana / Castellón de la Plana', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12050', 'Coves de Vinromà, les', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12085', 'Orpesa / Oropesa del Mar', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12094', 'Pobla Tornesa, la', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12103', 'Sarratella', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12105', 'Sierra Engarcerán', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12117', 'Torreblanca', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12120', 'Torre d''en Doménec, la', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12124', 'Vall d''Alba', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12128', 'Vilafamés', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12132', 'Vilanova d''Alcolea', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12902', 'Sant Joan de Moró', '14');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3011', 'Alfàs del Pi, l''', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3018', 'Altea', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3027', 'Beniardá', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3031', 'Benidorm', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3033', 'Benifato', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3037', 'Benimantell', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3045', 'Bolulla', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3048', 'Callosa d''En Sarrià', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3057', 'Confrides', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3069', 'Finestrat', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3075', 'Castell de Guadalest, el', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3094', 'Nucia, la', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3098', 'Orxeta', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3107', 'Polop', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3112', 'Relleu', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3124', 'Sella', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3127', 'Tàrbena', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3139', 'Vila Joiosa, la / Villajoyosa', '6');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3001', 'Adsubia', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3006', 'Alcalalí', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3026', 'Beniarbeig', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3029', 'Benigembla', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3030', 'Benidoleig', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3040', 'Benimeli', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3041', 'Benissa', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3042', 'Poble Nou de Benitatxell, el / Benitachell', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3047', 'Calp', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3054', 'Castell de Castells', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3063', 'Dénia', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3071', 'Gata de Gorgos', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3081', 'Xaló', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3082', 'Xàbia / Jávea', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3085', 'Llíber', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3091', 'Murla', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3095', 'Ondara', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3097', 'Orba', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3100', 'Parcent', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3101', 'Pedreguer', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3102', 'Pego', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3110', 'Ràfol d''Almúnia, el', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3115', 'Sagra', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3117', 'Sanet y Negrals', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3125', 'Senija', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3128', 'Teulada', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3131', 'Tormos', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3134', 'Vall d''Alcalà, la', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3135', 'Vall d’Ebo, la', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3136', 'Vall de Gallinera', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3137', 'Vall de Laguar, la', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3138', 'Verger, el', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3901', 'Poblets, els', '5');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46012', 'Alborache', '24');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46077', 'Buñol', '24');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46109', 'Cheste', '24');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46111', 'Chiva', '24');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46115', 'Dos Aguas', '24');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46136', 'Godelleta', '24');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46158', 'Macastre', '24');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46229', 'Siete Aguas', '24');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46261', 'Yátova', '24');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46020', 'Alcúdia de Crespins, l''', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46045', 'Barxeta', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46081', 'Canals', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46096', 'Cerdà', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46121', 'Estubeny', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46128', 'Font de la Figuera, la', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46132', 'Genovés', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46137', 'Granja de la Costera, la', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46145', 'Xàtiva', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46151', 'Llocnou d''En Fenollet', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46154', 'Llanera de Ranes', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46157', 'Llosa de Ranes, la', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46170', 'Moixent / Mogente', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46174', 'Montesa', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46180', 'Novetlè / Novelé', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46217', 'Rotglà i Corberà', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46243', 'Torrella', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46251', 'Vallada', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46253', 'Vallés', '23');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46039', 'Anna', '22');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46071', 'Bicorp', '22');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46073', 'Bolbaite', '22');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46107', 'Chella', '22');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46118', 'Enguera', '22');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46167', 'Millares', '22');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46179', 'Navarrés', '22');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46206', 'Quesa', '22');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46007', 'Albal', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46015', 'Alcàsser', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46022', 'Alfafar', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46054', 'Benetússer', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46065', 'Beniparrell', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46094', 'Catarroja', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46152', 'Llocnou de la Corona', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46165', 'Massanassa', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46186', 'Paiporta', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46194', 'Picassent', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46223', 'Sedaví', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46230', 'Silla', '32');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46005', 'Alaquàs', '31');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46021', 'Aldaia', '31');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46102', 'Quart de Poblet', '31');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46110', 'Xirivella', '31');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46159', 'Manises', '31');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46169', 'Mislata', '31');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46190', 'Paterna', '31');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46193', 'Picanya', '31');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46244', 'Torrent', '31');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46009', 'Albalat dels Sorells', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46013', 'Alboraya', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46014', 'Albuixech', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46025', 'Alfara del Patriarca', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46032', 'Almàssera', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46074', 'Bonrepòs i Mirambell', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46078', 'Burjassot', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46117', 'Emperador', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46126', 'Foios', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46135', 'Godella', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46163', 'Massalfassar', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46164', 'Massamagrell', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46166', 'Meliana', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46171', 'Moncada', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46177', 'Museros', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46199', 'Pobla de Farnals, la', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46204', 'Puig', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46205', 'Puçol', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46207', 'Rafelbunyol / Rafelbuñol', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46216', 'Rocafort', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46237', 'Tavernes Blanques', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46260', 'Vinalesa', '30');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3023', 'Beneixama', '9');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3043', 'Biar', '9');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3051', 'Camp de Mirra, el / Campo de Mirra', '9');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3052', 'Cañada', '9');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3116', 'Salinas', '9');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3123', 'Sax', '9');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3140', 'Villena', '9');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12003', 'Albocàsser', '17');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12014', 'Ares del Maestrat', '17');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12026', 'Benasal', '17');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12042', 'Catí', '17');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12051', 'Culla', '17');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12111', 'Tírig', '17');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12119', 'Torre d''En Besora, la', '17');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12129', 'Villafranca del Cid / Vilafranca', '17');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12134', 'Vilar de Canes', '17');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3009', 'Alcoi / Alcoy', '8');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3021', 'Banyeres de Mariola', '8');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3032', 'Benifallim', '8');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3053', 'Castalla', '8');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3079', 'Ibi', '8');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3096', 'Onil', '8');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3103', 'Penàguila', '8');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3129', 'Tibi', '8');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12001', 'Atzeneta del Maestrat', '16');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12005', 'Alcora, l''', '16');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12025', 'Benafigos', '16');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12049', 'Costur', '16');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12055', 'Xodos / Chodos', '16');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12060', 'Figueroles', '16');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12072', 'Lucena del Cid', '16');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12122', 'Useres, les / Useras', '16');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12139', 'Vistabella del Maestrazgo', '16');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3002', 'Agost', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3004', 'Aigües', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3014', 'Alacant / Alicante', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3046', 'Busot', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3050', 'Campello, el', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3083', 'Xixona / Jijona', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3090', 'Mutxamel', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3119', 'Sant Joan d''Alacant', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3122', 'Sant Vicent del Raspeig / San Vicente del Raspeig', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3132', 'Torre de les Maçanes, la / Torremanzanas', '7');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12038', 'Castellfort', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12045', 'Cinctorres', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12061', 'Forcall', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12068', 'Herbés', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12075', 'Mata, la', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12080', 'Morella', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12083', 'Olocau del Rey', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12087', 'Palanques', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12091', 'Portell de Morella', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12112', 'Todolella', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12127', 'Vallibona', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12137', 'Villores', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12141', 'Zorita del Maestrazgo', '13');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3013', 'Algueña', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3019', 'Aspe', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3066', 'Elda', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3077', 'Fondó de les Neus, el / Hondón de las Nieves', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3078', 'Hondón de los Frailes', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3088', 'Monforte del Cid', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3089', 'Monòver / Monóvar', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3093', 'Novelda', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3104', 'Petrer', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3105', 'Pinós, el / Pinoso', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3114', 'Romana, la', '4');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46044', 'Ayora', '21');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46097', 'Cofrentes', '21');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46099', 'Cortes de Pallás', '21');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46142', 'Jalance', '21');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46144', 'Jarafuel', '21');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46239', 'Teresa de Cofrentes', '21');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46263', 'Zarra', '21');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46001', 'Ademuz', '20');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46087', 'Casas Altas', '20');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46088', 'Casas Bajas', '20');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46092', 'Castielfabib', '20');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46201', 'Puebla de San Miguel', '20');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46242', 'Torrebaja', '20');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46252', 'Vallanca', '20');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3003', 'Agres', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3007', 'Alcocer de Planes', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3008', 'Alcoleja', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3010', 'Alfafara', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3016', 'Almudaina', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3017', 'Alqueria d''Asnar, l''', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3020', 'Balones', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3022', 'Benasau', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3028', 'Beniarrés', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3035', 'Benilloba', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3036', 'Benillup', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3038', 'Benimarfull', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3039', 'Benimassot', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3056', 'Cocentaina', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3060', 'Quatretondeta', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3067', 'Facheca', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3068', 'Famorca', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3072', 'Gaianes', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3073', 'Gorga', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3084', 'Orxa, l'' / Lorcha', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3086', 'Millena', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3092', 'Muro de Alcoy', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3106', 'Planes', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3130', 'Tollos', '3');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46051', 'Benaguasil', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46067', 'Benisanó', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46070', 'Bétera', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46089', 'Casinos', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46116', 'Eliana, l''', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46147', 'Llíria', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46148', 'Loriguilla', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46161', 'Marines', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46178', 'Náquera', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46182', 'Olocau', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46202', 'Pobla de Vallbona, la', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46214', 'Riba-roja de Túria', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46228', 'Serra', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46256', 'Vilamarxant', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46902', 'Gátova', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46903', 'San Antonio de Benagéber', '19');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46010', 'Albalat dels Tarongers', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46024', 'Alfara de la Baronia', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46028', 'Algar de Palancia', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46030', 'Algimia de Alfara', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46052', 'Benavites', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46058', 'Benifairó de les Valls', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46082', 'Canet d''En Berenguer', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46101', 'Quart de les Valls', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46103', 'Quartell', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46120', 'Estivella', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46122', 'Faura', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46134', 'Gilet', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46192', 'Petrés', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46220', 'Sagunt / Sagunto', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46224', 'Segart', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('46245', 'Torres Torres', '18');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3059', 'Crevillent', '2');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3065', 'Elx / Elche', '2');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3121', 'Santa Pola', '2');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3005', 'Albatera', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3012', 'Algorfa', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3015', 'Almoradí', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3024', 'Benejúzar', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3025', 'Benferri', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3034', 'Benijófar', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3044', 'Bigastro', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3049', 'Callosa de Segura', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3055', 'Catral', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3058', 'Cox', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3061', 'Daya Nueva', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3062', 'Daya Vieja', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3064', 'Dolores', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3070', 'Formentera del Segura', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3074', 'Granja de Rocamora', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3076', 'Guardamar del Segura', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3080', 'Jacarilla', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3099', 'Orihuela', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3109', 'Rafal', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3111', 'Redován', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3113', 'Rojales', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3118', 'San Fulgencio', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3120', 'San Miguel de Salinas', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3133', 'Torrevieja', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3902', 'Pilar de la Horadada', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3903', 'Montesinos, Los', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('3904', 'San Isidro', '1');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12004', 'Alcalà de Xivert', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12027', 'Benicarló', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12034', 'Càlig', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12036', 'Canet lo Roig', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12037', 'Castell de Cabres', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12044', 'Cervera del Maestre', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12052', 'Xert / Chert', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12070', 'Jana, la', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12089', 'Peníscola / Peñíscola', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12093', 'Pobla de Benifassà, la', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12096', 'Rossell', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12098', 'Salzadella, la', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12099', 'Sant Jordi / San Jorge', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12100', 'Sant Mateu', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12101', 'San Rafael del Río', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12102', 'Santa Magdalena de Pulpis', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12121', 'Traiguera', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12138', 'Vinaròs', '12');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12008', 'Algimia de Almonacid', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12010', 'Almedíjar', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12012', 'Altura', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12018', 'Azuébar', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12020', 'Barracas', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12022', 'Bejís', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12024', 'Benafer', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12039', 'Castellnovo', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12043', 'Caudiel', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12056', 'Chóvar', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12065', 'Gaibiel', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12067', 'Geldo', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12069', 'Higueras', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12071', 'Jérica', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12076', 'Matet', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12081', 'Navajas', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12088', 'Pavías', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12090', 'Pina de Montalgrao', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12097', 'Sacañet', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12104', 'Segorbe', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12106', 'Soneja', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12107', 'Sot de Ferrer', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12110', 'Teresa', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12114', 'Torás', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12115', 'Toro, El', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12125', 'Vall de Almonacid', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12140', 'Viver', '11');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12013', 'Arañuel', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12015', 'Argelita', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12017', 'Ayódar', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12041', 'Castillo de Villamalefa', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12046', 'Cirat', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12048', 'Cortes de Arenoso', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12058', 'Espadilla', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12059', 'Fanzara', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12063', 'Fuente la Reina', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12064', 'Fuentes de Ayódar', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12073', 'Ludiente', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12078', 'Montán', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12079', 'Montanejos', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12092', 'Puebla de Arenoso', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12113', 'Toga', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12116', 'Torralba del Pinar', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12118', 'Torrechiva', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12123', 'Vallat', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12130', 'Villahermosa del Río', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12131', 'Villamalur', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12133', 'Villanueva de Viver', '10');"
                        + "INSERT INTO \"public\".\"towns\" VALUES ('12142', 'Zucaina', '10');");
        queryTowns.executeUpdate();

        // Close the connection
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void loadUsersAndAuthorities(){
        // Open a transaction
        EntityManagerFactory factory = (EntityManagerFactory) context.getBean("entityManagerFactory");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Authority userAuthority = new Authority();
        userAuthority.setName("ROLE_USER");
        authorityDAO.persist(userAuthority);

        User defaultUser = new User();
        defaultUser.setName("SIA");
        defaultUser.setSurname("Default User");
        defaultUser.setDni("12345678A");
        defaultUser.setUsername("sia");
        //Password in MD5
        String password  = "agricultura.1";
        defaultUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        defaultUser.setActive(true);
        defaultUser.setTown(townDAO.findByName("Alcàsser"));
        defaultUser.setAuthority(userAuthority);
        userDAO.persist(defaultUser);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void loadUses(){
        // Open a transaction
        EntityManagerFactory factory = (EntityManagerFactory) context.getBean("entityManagerFactory");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Query queryUses = entityManager
                .createNativeQuery("INSERT INTO \"public\".\"uses\" VALUES ('CF', 'Asociación Cítricos - Frutales');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('CS', 'Asociación Cítricos - Frutales de Cáscara');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('CV', 'Asociación Cítricos - Viñedo');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('FF', 'Asociación Frutales - Frutales De Cáscara');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('OC', 'Asociación Olivar - Cítricos');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('CI', 'Cítricos');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('AG', 'Corrientes y Superficies de Agua');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('ED', 'Edificaciones');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('FO', 'Forestal');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('FY', 'Frutales');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('FS', 'Frutos Secos');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('FL', 'Frutos Secos y Olivar');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('FV', 'Frutos Secos y Viñedo');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('TH', 'Huerta');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('IM', 'Improductivos');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('IV', 'Invernaderos y cultivos bajo plastico');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('OV', 'Olivar');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('OF', 'Olivar - Frutal');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('PS', 'Pastizal');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('PR', 'Pasto Arbustivo');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('PA', 'Pasto con Arbolado');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('TA', 'Tierras Arables');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('CA', 'Viales');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('VI', 'Viñedo');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('VF', 'Viñedo - Frutal');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('VO', 'Viñedo - Olivar');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('ZV', 'Zona Censurada');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('ZF', 'Zona Concentrada no incluida en la Ortofoto');" +
                        "INSERT INTO \"public\".\"uses\" VALUES ('ZI', 'Zona Urbana');");
        queryUses.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void loadVarieties(){
        // Open a transaction
        EntityManagerFactory factory = (EntityManagerFactory) context.getBean("entityManagerFactory");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Query queryVarieties = entityManager
                .createNativeQuery("INSERT INTO \"public\".\"varieties\" (id, name,description) VALUES ('1', 'Marisol', 'La Clementina Marisol es una mandarina precoz, de color rojo intenso y fácil de pelar, con gran cantidad de zumo aromático y dulce pero con un toque de acidez.  Su pulpa es tierna, fundente y sin semillas ');" +
                        "INSERT INTO \"public\".\"varieties\" (id, name,description) VALUES ('2','Hernandina', 'Mutación de Clementina Fina originada en Picassent (Valencia). El árbol es vigoroso, con la madera algo frágil y sin espinas. La viabilidad del polen es alta. La variedad es partenocárpica y autoincompatible');" +
//                        "INSERT INTO \"public\".\"varieties\" (id, name,description) VALUES ('3','Nave Late', 'Mutación de Washington originada en Australia. Árbol vigoroso, con alguna espina en las ramas de mayor vigor. Las flores carecen de polen y al igual que el resto de variedades del grupo navel, los frutos presentan ombligo.');" +
                        "INSERT INTO \"public\".\"sequence_store\" VALUES('VARIETIES_PK',(SELECT MAX(id)+1 FROM \"public\".\"varieties\"));"
                );

        queryVarieties.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void loadTestData(){
        // Open a transaction
        EntityManagerFactory factory = (EntityManagerFactory) context.getBean("entityManagerFactory");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        User defaultUser = userDAO.findByUsername("sia");
        //Field #1
        Field f1 = new Field();
        f1.setName("Field #1");
        //Parcel
        Parcel p1 = new Parcel();
        p1.setParcel(1);
        p1.setPolygon(1);
        p1.setAggregate(1);
        p1.setZone(1);
        p1.setTown(townDAO.findByName("Picassent"));
        p1.setArea(25F);
        Coordinates cP1 = new Coordinates();
        cP1.setX(36D);
        cP1.setY(45D);
        cP1.setDatum("DATUM1");
        cP1.setSpindle(29);
        p1.setCoordinates(cP1);
        //Enclosure
        //#1
        Enclosure e1 = new Enclosure();
        e1.setEnclosure(1);
        e1.setCoordinates(cP1);
        e1.setArea(20F);
        e1.setIrrigationCoef(100);
        e1.setSlope(0F);
        e1.setUse(useDAO.findById("CI"));

        //Servers
        ArrayList<Server> servers = new ArrayList<>();
        Properties props = null;
        try {
            props = propertiesFactoryBean.getObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        servers.add(new Server(props.getProperty("default.siarest.host"),
                Integer.parseInt(props.getProperty("default.siarest.port")),
                props.getProperty("default.siarest.username"), props.getProperty("default.siarest.password")));
        servers.add(new Server(props.getProperty("default.siarest.host"),
                Integer.parseInt(props.getProperty("default.siarest.port")),
                props.getProperty("default.siarest.username"), props.getProperty("default.siarest.password")));
        
        //Boards
        ArrayList<Board> boards =  new ArrayList<>();
        boards.add(new Board(1, "USB", "/dev/cu.usbmodem1411", "55ecb0a13cfac3d641ce0379"));
        boards.add(new Board(2, "LAN", "192.168.1.3:8080", "Test Board Ethernet"));
        boards.add(new Board(3, "USB", "/dev/cu.usbmodem1412", "OtroId"));

        //Sensors 0001
        ArrayList<Sensor> sensors = new ArrayList<>();

        //Temperature
        sensors.add(new DS18B20("28f5e9af020000d2"));
        sensors.add(new DS18B20("282ddbaf020000b0"));
        sensors.add(new Temperature("T_01", "Temperature"));

        //Pressure
        sensors.add(new BMP085("bmp085_01"));

        //Humidity
        sensors.add(new HH10D("HH10D_01"));
        sensors.add(new SoilMoisture("M_01"));

        //Actuators 0001
        ArrayList<Actuator> actuators = new ArrayList<>();
        Actuator actuator;
        //Actuator
        actuator = new Actuator("1");
        actuator.setDescription("Test actuator #1");
        actuator.setEnabled(true);
        actuators.add(actuator);

        //Basic Actuator
        actuator = new BasicActuator("2");
        actuator.setDescription("Test actuator #2");
        actuator.setEnabled(false);
        actuators.add(actuator);

        //#2
        Enclosure e2 = new Enclosure();
        e2.setEnclosure(2);
        e2.setCoordinates(new Coordinates(37D, 45.5D, "DATUM1", 29));
        e2.setArea(5F);
        e2.setIrrigationCoef(100);
        e2.setSlope(0F);
        e2.setUse(useDAO.findById("CI"));
        //Set relations
        e1.setParcel(p1);
        e2.setParcel(p1);
        p1.setField(f1);
        f1.setUser(defaultUser);

        fieldDAO.persist(f1);
        parcelDAO.persist(p1);
        enclosureDAO.persist(e1);
        enclosureDAO.persist(e2);

        servers.get(0).setEnclosure(e1);
        servers.get(1).setEnclosure(e2);
        serverDAO.persist(servers.get(0));
        serverDAO.persist(servers.get(1));

        boards.get(0).setServer(servers.get(0));
        boards.get(2).setServer(servers.get(0));
        boards.get(1).setServer(servers.get(1));
        boardDAO.persist(boards.get(0));
        boardDAO.persist(boards.get(2));
        boardDAO.persist(boards.get(1));

        for(Sensor i : sensors) {
            Random rm = new Random();
            i.setValue(rm.nextDouble() * 100);
            i.setBoard(boards.get(0));
            sensorDAO.persist(i);
        }
        for(Actuator i: actuators) {
            i.setBoard(boards.get(0));
            actuatorDAO.persist(i);
        }
        //Field #2
        Field f2 = new Field();
        f2.setName("Field #2");
        //Parcel #1
        Parcel p2_1 = new Parcel();
        p2_1.setParcel(2);
        p2_1.setPolygon(1);
        p2_1.setAggregate(4);
        p2_1.setZone(4);
        p2_1.setTown(townDAO.findByName("Alcàsser"));
        p2_1.setArea(2F);
        p2_1.setCoordinates(new Coordinates(32D,40D,"DATUM1",29));
        //Enclosure
        Enclosure e2_1 = new Enclosure();
        e2_1.setEnclosure(1);
        e2_1.setCoordinates(new Coordinates(32D, 40D, "DATUM1", 29));
        e2_1.setArea(2F);
        e2_1.setIrrigationCoef(100);
        e2_1.setSlope(0F);
        e2_1.setUse(useDAO.findById("IM"));

        //Parcel #2
        Parcel p2_2 = new Parcel();
        p2_2.setParcel(3);
        p2_2.setPolygon(1);
        p2_2.setAggregate(4);
        p2_2.setZone(4);
        p2_2.setTown(townDAO.findByName("Alcàsser"));
        p2_2.setArea(12F);
        p2_2.setCoordinates(new Coordinates(33.4D,40D,"DATUM1",29));
        //Enclosure
        //#1
        Enclosure e2_2 = new Enclosure();
        e2_2.setEnclosure(1);
        e2_2.setCoordinates(new Coordinates(33.4D, 40D, "DATUM1", 29));
        e2_2.setArea(10F);
        e2_2.setIrrigationCoef(100);
        e2_2.setSlope(0F);
        e2_2.setUse(useDAO.findById("CI"));
        //#2
        Enclosure e2_3 = new Enclosure();
        e2_3.setEnclosure(2);
        e2_3.setCoordinates(new Coordinates(33.4D, 40D, "DATUM1", 29));
        e2_3.setArea(2F);
        e2_3.setIrrigationCoef(90);
        e2_3.setSlope(0F);
        e2_3.setUse(useDAO.findById("ED"));

        //Set relations
        e2_1.setParcel(p2_1);
        p2_1.setField(f2);
        e2_2.setParcel(p2_2);
        e2_3.setParcel(p2_2);
        p2_2.setField(f2);
        f2.setUser(defaultUser);

        fieldDAO.persist(f2);
        parcelDAO.persist(p2_1);
        parcelDAO.persist(p2_2);
        enclosureDAO.persist(e2_1);
        enclosureDAO.persist(e2_2);
        enclosureDAO.persist(e2_3);

        User joherma1 = new User();
        joherma1.setUsername("joherma1");
        joherma1.setPassword("");

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void loadSampleData(){
        // Open a transaction
        EntityManagerFactory factory = (EntityManagerFactory) context.getBean("entityManagerFactory");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        User joherma1 = new User();
        joherma1.setUsername("joherma1");
        joherma1.setPassword("b92ed60847a306d48331f61afedd2df3"); //md5 encrypted
        joherma1.setActive(true);
        joherma1.setName("Jose Antonio");
        joherma1.setSurname("Hernández Martínez");
        joherma1.setMobile("687802637");
        joherma1.setAddress("Ricard Hernández 60");
        joherma1.setTown(townDAO.findByName("Alcàsser"));
        joherma1.setAuthority(authorityDAO.findByName("ROLE_USER"));
        userDAO.persist(joherma1);

        Field camiSueca = new Field();
        camiSueca.setName("Camí Sueca");
        camiSueca.setUser(joherma1);
        fieldDAO.persist(camiSueca);

        Parcel camiSuecaP1 = new Parcel();
        camiSuecaP1.setTown(townDAO.findByName("Picassent"));
        camiSuecaP1.setAggregate(0);
        camiSuecaP1.setZone(0);
        camiSuecaP1.setPolygon(12);
        camiSuecaP1.setParcel(11);
        camiSuecaP1.setField(camiSueca);
        camiSuecaP1.setCoordinates(new Coordinates(720614.46, 4359890, "WGS84", 30));
        parcelDAO.persist(camiSuecaP1);

        Enclosure camiSuecaP1E1 = new Enclosure();
        camiSuecaP1E1.setEnclosure(1);
        camiSuecaP1E1.setSlope(1.3F);
        camiSuecaP1E1.setIrrigationCoef(100);
        camiSuecaP1E1.setUse(useDAO.findById("CF"));
        camiSuecaP1E1.setCoordinates(camiSuecaP1.getCoordinates());
        camiSuecaP1E1.setParcel(camiSuecaP1);
        camiSuecaP1E1.setArea(0.232F);
        Variety hernandina = varietyDAO.findByName("hernandina");
        camiSuecaP1E1.setVariety(hernandina);
        enclosureDAO.persist(camiSuecaP1E1);

        Field aiguaSalada = new Field();
        aiguaSalada.setName("Aigua Salada");
        aiguaSalada.setUser(joherma1);
        fieldDAO.persist(aiguaSalada);

        Parcel aiguaSaladaP1 = new Parcel();
        aiguaSaladaP1.setTown(townDAO.findByName("Picassent"));
        aiguaSaladaP1.setAggregate(0);
        aiguaSaladaP1.setZone(0);
        aiguaSaladaP1.setPolygon(31);
        aiguaSaladaP1.setParcel(354);
        aiguaSaladaP1.setField(aiguaSalada);
        aiguaSaladaP1.setCoordinates(new Coordinates(714748.16, 4359983.19, "WGS84", 30));
        parcelDAO.persist(aiguaSaladaP1);

        Enclosure aiguaSaladaP1E1 = new Enclosure();
        aiguaSaladaP1E1.setEnclosure(1);
        aiguaSaladaP1E1.setSlope(1.8F);
        aiguaSaladaP1E1.setIrrigationCoef(100);
        aiguaSaladaP1E1.setUse(useDAO.findById("CF"));
        aiguaSaladaP1E1.setCoordinates(aiguaSaladaP1.getCoordinates());
        aiguaSaladaP1E1.setParcel(aiguaSaladaP1);
        aiguaSaladaP1E1.setArea(0.4031F);
        Variety navelLate = new Variety();
        navelLate.setName("Navel Late");
        navelLate.setDescription("Mutación de Washington originada en Australia. Árbol vigoroso, con alguna espina en las ramas de mayor vigor. Las flores carecen de polen y al igual que el resto de variedades del grupo navel, los frutos presentan ombligo.');");
        aiguaSaladaP1E1.setVariety(navelLate);
        varietyDAO.persist(navelLate);
        enclosureDAO.persist(aiguaSaladaP1E1);

        Field marisol = new Field();
        marisol.setName("Marisol Rampa");
        marisol.setUser(joherma1);
        fieldDAO.persist(marisol);

        Parcel marisolP1 = new Parcel();
        marisolP1.setTown(townDAO.findByName("Picassent"));
        marisolP1.setAggregate(0);
        marisolP1.setZone(0);
        marisolP1.setPolygon(52);
        marisolP1.setParcel(182);
        marisolP1.setField(marisol);
        marisolP1.setCoordinates(new Coordinates(718662.72, 4357744.62, "WGS84", 30));
        parcelDAO.persist(marisolP1);

        Enclosure marisolP1E1 = new Enclosure();
        marisolP1E1.setEnclosure(1);
        marisolP1E1.setSlope(2.8F);
        marisolP1E1.setIrrigationCoef(100);
        marisolP1E1.setUse(useDAO.findById("CF"));
        marisolP1E1.setCoordinates(marisolP1.getCoordinates());
        marisolP1E1.setParcel(marisolP1);
        marisolP1E1.setArea(0.3153F);
        marisolP1E1.setVariety(varietyDAO.findByName("marisol"));
        enclosureDAO.persist(marisolP1E1);


        //Paridera
        Field paridera = new Field();
        paridera.setName("Paridera");
        paridera.setUser(joherma1);
        fieldDAO.persist(paridera);

        //P1
        Parcel parideraP1 = new Parcel();
        parideraP1.setTown(townDAO.findByName("Picassent"));
        parideraP1.setAggregate(0);
        parideraP1.setZone(0);
        parideraP1.setPolygon(51);
        parideraP1.setParcel(175);
        parideraP1.setField(paridera);
        parideraP1.setCoordinates(new Coordinates(718692.56, 4358545.69, "WGS84", 30));
        parcelDAO.persist(parideraP1);

        Enclosure parideraP1E1 = new Enclosure();
        parideraP1E1.setEnclosure(1);
        parideraP1E1.setSlope(2.7F);
        parideraP1E1.setIrrigationCoef(100);
        parideraP1E1.setUse(useDAO.findById("CF"));
        parideraP1E1.setCoordinates(parideraP1.getCoordinates());
        parideraP1E1.setParcel(parideraP1);
        parideraP1E1.setArea(0.56F);
        parideraP1E1.setVariety(varietyDAO.findByName("hernandina"));
        enclosureDAO.persist(parideraP1E1);

        //P1
        Parcel parideraP2 = new Parcel();
        parideraP2.setTown(townDAO.findByName("Picassent"));
        parideraP2.setAggregate(0);
        parideraP2.setZone(0);
        parideraP2.setPolygon(51);
        parideraP2.setParcel(177);
        parideraP2.setField(paridera);
        parideraP2.setCoordinates(new Coordinates(718668.69, 4358511.47, "WGS84", 30));
        parcelDAO.persist(parideraP2);

        Enclosure parideraP2E1 = new Enclosure();
        parideraP2E1.setEnclosure(1);
        parideraP2E1.setSlope(2.8F);
        parideraP2E1.setIrrigationCoef(100);
        parideraP2E1.setUse(useDAO.findById("CF"));
        parideraP2E1.setCoordinates(parideraP2.getCoordinates());
        parideraP2E1.setParcel(parideraP2);
        parideraP2E1.setArea(0.1823F);
        parideraP2E1.setVariety(varietyDAO.findByName("hernandina"));
        enclosureDAO.persist(parideraP2E1);


        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
