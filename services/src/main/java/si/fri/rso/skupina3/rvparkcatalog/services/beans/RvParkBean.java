package si.fri.rso.skupina3.rvparkcatalog.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso.skupina3.rvparkcatalog.lib.RvPark;
import si.fri.rso.skupina3.rvparkcatalog.models.converters.RvParkConverter;
import si.fri.rso.skupina3.rvparkcatalog.models.entities.RvParkEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class RvParkBean {

    private final Logger log = Logger.getLogger(RvParkBean.class.getName());

    @Inject
    private EntityManager em;

    public List<RvPark> getRvParks(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, RvParkEntity.class, queryParameters).stream()
                .map(RvParkConverter::toDto).collect(Collectors.toList());

    }

    public RvPark getRvPark(Integer rvParkId) {
        RvParkEntity rvParkEntity = em.find(RvParkEntity.class, rvParkId);

        if (rvParkEntity == null) {
            throw new NotFoundException();
        }

        return RvParkConverter.toDto(rvParkEntity);
    }

    public RvPark createRvPark(RvPark rvPark) {

        RvParkEntity rvParkEntity = RvParkConverter.toEntity(rvPark);

        try {
            beginTx();
            em.persist(rvParkEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (rvParkEntity.getRv_park_id() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return RvParkConverter.toDto(rvParkEntity);
    }

    public RvPark updateRvPark(Integer rvParkId, RvPark rvPark) {

        RvParkEntity rvParkEntity = em.find(RvParkEntity.class, rvParkId);

        if (rvParkEntity == null) {
            return null;
        }

        RvParkEntity updatedRvParkEntity = RvParkConverter.toEntity(rvPark);

        try {
            beginTx();
            updatedRvParkEntity.setRv_park_id(rvParkId);
            em.merge(updatedRvParkEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return RvParkConverter.toDto(updatedRvParkEntity);
    }

    public boolean deleteRvPark(Integer rvParkId) {

        RvParkEntity rvParkEntity = em.find(RvParkEntity.class, rvParkId);

        if (rvParkEntity != null) {
            try {
                beginTx();
                em.remove(rvParkEntity);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }





    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
