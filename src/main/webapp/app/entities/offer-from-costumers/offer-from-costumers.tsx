import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './offer-from-costumers.reducer';
import { IOfferFromCostumers } from 'app/shared/model/offer-from-costumers.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOfferFromCostumersProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const OfferFromCostumers = (props: IOfferFromCostumersProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { offerFromCostumersList, match, loading } = props;
  return (
    <div>
      <h2 id="offer-from-costumers-heading" data-cy="OfferFromCostumersHeading">
        Offer From Costumers
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Offer From Costumers
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {offerFromCostumersList && offerFromCostumersList.length > 0 ? (
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
                <th>T G User</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {offerFromCostumersList.map((offerFromCostumers, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${offerFromCostumers.id}`} color="link" size="sm">
                      {offerFromCostumers.id}
                    </Button>
                  </td>
                  <td>{offerFromCostumers.text}</td>
                  <td>{offerFromCostumers.isDelete ? 'true' : 'false'}</td>
                  <td>{offerFromCostumers.adminId}</td>
                  <td>{offerFromCostumers.isActive ? 'true' : 'false'}</td>
                  <td>
                    {offerFromCostumers.date1 ? <TextFormat type="date" value={offerFromCostumers.date1} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {offerFromCostumers.date2 ? <TextFormat type="date" value={offerFromCostumers.date2} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{offerFromCostumers.long1}</td>
                  <td>{offerFromCostumers.string1}</td>
                  <td>{offerFromCostumers.boolean1 ? 'true' : 'false'}</td>
                  <td>
                    {offerFromCostumers.tGUser ? (
                      <Link to={`tg-user/${offerFromCostumers.tGUser.id}`}>{offerFromCostumers.tGUser.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${offerFromCostumers.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${offerFromCostumers.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${offerFromCostumers.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Offer From Costumers found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ offerFromCostumers }: IRootState) => ({
  offerFromCostumersList: offerFromCostumers.entities,
  loading: offerFromCostumers.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OfferFromCostumers);
