import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './category-log.reducer';
import { ICategoryLog } from 'app/shared/model/category-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategoryLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CategoryLog = (props: ICategoryLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { categoryLogList, match, loading } = props;
  return (
    <div>
      <h2 id="category-log-heading" data-cy="CategoryLogHeading">
        Category Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Category Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {categoryLogList && categoryLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Count Chanell In Category</th>
                <th>Is Delete</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>Category</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {categoryLogList.map((categoryLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${categoryLog.id}`} color="link" size="sm">
                      {categoryLog.id}
                    </Button>
                  </td>
                  <td>{categoryLog.name}</td>
                  <td>{categoryLog.countChanellInCategory}</td>
                  <td>{categoryLog.isDelete ? 'true' : 'false'}</td>
                  <td>{categoryLog.date1 ? <TextFormat type="date" value={categoryLog.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{categoryLog.date2 ? <TextFormat type="date" value={categoryLog.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{categoryLog.long1}</td>
                  <td>{categoryLog.string1}</td>
                  <td>{categoryLog.boolean1 ? 'true' : 'false'}</td>
                  <td>{categoryLog.category ? <Link to={`category/${categoryLog.category.id}`}>{categoryLog.category.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${categoryLog.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${categoryLog.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${categoryLog.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Category Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ categoryLog }: IRootState) => ({
  categoryLogList: categoryLog.entities,
  loading: categoryLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CategoryLog);
