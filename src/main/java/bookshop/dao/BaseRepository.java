package bookshop.dao;

import java8.util.Optional;
import bookshop.util.GenericUtils;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO 请求层
 *
 * @param <T>
 */
@Stateless
public class BaseRepository<T extends IEntity<ID>, ID> implements IRepository<T, ID>, Serializable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BaseRepository.class);
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 对应实体类
     */
    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseRepository() {
        this.entityClass = (Class<T>) GenericUtils.getSuperClassGenericType(getClass(), 0);
    }

    /**
     * 通过 ID 获得一个对象
     */
    public T getOne(ID id) {
        return this.entityManager.find(this.entityClass, id);
    }

    /**
     * 通过 ID 获得对象
     */
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(getOne(id));
    }

    /**
     * 通过 ID 序列获得对象
     */
    public List<T> findAllById(Iterable<ID> ids) {
        if (ids == null) {
            throw new NullPointerException();
        }

        ArrayList<T> entityList = new ArrayList<T>();
        for (ID id : ids) {
            T entity = getOne(id);
            entityList.add(entity);
        }
        return entityList;
    }

    /**
     * 获得所有对象
     */
    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.entityClass);
        Root<T> root = criteriaQuery.from(this.entityClass);
        criteriaQuery.select(root);

        TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * TODO 分页获得所有对象
     */
    public List<T> findAll(Pageable page) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.entityClass);
        Root<T> root = criteriaQuery.from(this.entityClass);
        criteriaQuery.select(root);

        TypedQuery<T> query = this.entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * 保存对象
     */
    public <S extends T> S save(S object) {
        S savedObject;

        ID id = object.getId();
        if (id != null) {
            log.info("合并已存在对象 {}, ID = {}", this.entityClass.getName(), id);
            savedObject = this.entityManager.merge(object);
        } else {
            log.info("对象不存在，持久化 {}", this.entityClass.getName());
            this.entityManager.persist(object);
            savedObject = object;
            log.info("持久化结果 ID = {}", savedObject.getId());
        }

        return savedObject;
    }

    /**
     * 保存对象序列
     */
    public <S extends T> Iterable<S> saveAll(Iterable<S> objects) {
        if (objects == null) {
            throw new NullPointerException();
        }

        ArrayList<S> entityList = new ArrayList<S>();
        for (S entity : objects) {
            S savedEntity = save(entity);
            entityList.add(savedEntity);
        }
        return entityList;
    }

    /**
     * 通过 ID 删除对象
     */
    public void deleteById(ID id) {
        T entity = getOne(id);
        if (entity != null) {
            delete(entity);
        }
    }

    /**
     * 通过 ID 序列删除对象
     */
    public void deleteAllById(Iterable<ID> ids) {
        if (ids == null) {
            throw new NullPointerException();
        }

        for (ID id : ids) {
            deleteById(id);
        }
    }

    /**
     * 删除实体对象
     */
    public void delete(T entity) {
        this.entityManager.remove(entity);
        log.info("删除对象 {}, ID = {}", this.entityClass.getName(), entity.getId());
    }

    /**
     * 删除实体对象序列
     */
    public void deleteAll(Iterable<? extends T> objects) {
        if (objects == null) {
            throw new NullPointerException();
        }

        for (T entity : objects) {
            delete(entity);
        }
    }

    /**
     * 删除所有实体
     */
    public void deleteAll() {
        List<T> entities = findAll();
        for (T entity : entities) {
            delete(entity);
        }
    }

    /**
     * 通过 SQL 查询一个结果
     */
    protected <R> Optional<R> findOneBySql(String sql, Class<R> resultClz, Object... args) {
        EntityManager manager = getEntityManager();
        TypedQuery<R> query = manager.createQuery(sql, resultClz);
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i + 1, args[i]);
        }
        query.setFirstResult(0);
        query.setMaxResults(1);
        List<R> list = query.getResultList();
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(0));
    }

    /**
     * 通过 SQL 查询全部
     */
    protected <R> List<R> findAllBySql(String sql, Class<R> resultClz, Object... args) {
        EntityManager manager = getEntityManager();
        TypedQuery<R> query = manager.createQuery(sql, resultClz);
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i + 1, args[i]);
        }
        return query.getResultList();
    }

    /**
     * 通过 SQL 查询全部
     */
    protected <R> List<R> findAllBySql(String sql, Pageable page, Class<R> resultClz, Object... args) {
        EntityManager manager = getEntityManager();
        TypedQuery<R> query = manager.createQuery(sql, resultClz);
        for (int i = 0; i < args.length; i++) {
            query.setParameter(i + 1, args[i]);
        }
        if (page != null) {
            query.setFirstResult(page.getOffset());
            query.setMaxResults(page.getPageSize());
        }
        return query.getResultList();
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}