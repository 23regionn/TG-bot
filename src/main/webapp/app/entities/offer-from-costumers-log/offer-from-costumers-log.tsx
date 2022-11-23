import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './offer-from-costumers-log.reducer';
import { IOfferFromCostumersLog } from 'app/shared/model/offer-from-costumers-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOfferFromCostumersLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const OfferFromCostumersLog = (props: IOfferFromCostumersLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { offerFromCostumersLogList, match, loading } = props;
  return (
    <div>
      <h2 id="offer-from-costumers-log-heading" data-cy="OfferFromCostumersLogHeading">
        Offer From Costumers Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Offer From Costumers Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {offerFromCostumersLogList && offerFromCostumersLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Text</th>
                <th>Is Delete</th>
                <th>Admin Id</th>
                <th>Is Active</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>Offer From Costumers</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {offerFromCostumersLogList.map((offerFromCostumersLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${offerFromCostumersLog.id}`} color="link" size="sm">
                      {offerFromCostumersLog.id}
                    </Button>
                  </td>
                  <td>{offerFromCostumersLog.text}</td>
                  <td>{offerFromCostumersLog.isDelete ? 'true' : 'false'}</td>
                  <td>{offerFromCostumersLog.adminId}</td>
                  <td>{offerFromCostumersLog.isActive ? 'true' : 'false'}</td>
                  <td>
                    {offerFromCostumersLog.date1 ? (
                      <TextFormat type="date" value={offerFromCostumersLog.date1} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {offerFromCostumersLog.date2 ? (
                      <TextFormat type="date" value={offerFromCostumersLog.date2} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{offerFromCostumersLog.long1}</td>
                  <td>{offerFromCostumersLog.string1}</td>
                  <td>{offerFromCostumersLog.boolean1 ? 'true' : 'false'}</td>
                  <td>
                    {offerFromCostumersLog.offerFromCostumers ? (
                      <Link to={`offer-from-costumers/${offerFromCostumersLog.offerFromCostumers.id}`}>
                        {offerFromCostumersLog.offerFromCostumers.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`${match.url}/${offerFromCostumersLog.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${offerFromCostumersLog.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${offerFromCostumersLog.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Offer From Costumers Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ offerFromCostumersLog }: IRootState) => ({
  offerFromCostumersLogList: offerFromCostumersLog.entities,
  loading: offerFromCostumersLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OfferFromCostumersLog);
