import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './pays.reducer';
import { IPays } from 'app/shared/model/pays.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPaysProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Pays = (props: IPaysProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { paysList, match, loading } = props;
  return (
    <div>
      <h2 id="pays-heading" data-cy="PaysHeading">
        Pays
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Pays
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {paysList && paysList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Date Pays Subscriptions</th>
                <th>Link</th>
                <th>Sum For Pays</th>
                <th>Type Buy</th>
                <th>Category</th>
                <th>Type Buy Long</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>T G User</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {paysList.map((pays, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${pays.id}`} color="link" size="sm">
                      {pays.id}
                    </Button>
                  </td>
                  <td>
                    {pays.datePaysSubscriptions ? (
                      <TextFormat type="date" value={pays.datePaysSubscriptions} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{pays.link}</td>
                  <td>{pays.sumForPays}</td>
                  <td>{pays.typeBuy}</td>
                  <td>{pays.category}</td>
                  <td>{pays.typeBuyLong}</td>
                  <td>{pays.date1 ? <TextFormat type="date" value={pays.date1} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{pays.date2 ? <TextFormat type="date" value={pays.date2} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{pays.long1}</td>
                  <td>{pays.string1}</td>
                  <td>{pays.boolean1 ? 'true' : 'false'}</td>
                  <td>{pays.tGUser ? <Link to={`tg-user/${pays.tGUser.id}`}>{pays.tGUser.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pays.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pays.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pays.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Pays found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ pays }: IRootState) => ({
  paysList: pays.entities,
  loading: pays.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Pays);
