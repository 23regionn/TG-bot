import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './members-trade-deal.reducer';
import { IMembersTradeDeal } from 'app/shared/model/members-trade-deal.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMembersTradeDealProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MembersTradeDeal = (props: IMembersTradeDealProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { membersTradeDealList, match, loading } = props;
  return (
    <div>
      <h2 id="members-trade-deal-heading" data-cy="MembersTradeDealHeading">
        Members Trade Deals
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Members Trade Deal
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {membersTradeDealList && membersTradeDealList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Tg User Id Current</th>
                <th>Price Offer</th>
                <th>Current Date</th>
                <th>Is Winner</th>
                <th>Is Delete</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>Trade Shop</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {membersTradeDealList.map((membersTradeDeal, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${membersTradeDeal.id}`} color="link" size="sm">
                      {membersTradeDeal.id}
                    </Button>
                  </td>
                  <td>{membersTradeDeal.tgUserIdCurrent}</td>
                  <td>{membersTradeDeal.priceOffer}</td>
                  <td>
                    {membersTradeDeal.currentDate ? (
                      <TextFormat type="date" value={membersTradeDeal.currentDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{membersTradeDeal.isWinner ? 'true' : 'false'}</td>
                  <td>{membersTradeDeal.isDelete ? 'true' : 'false'}</td>
                  <td>
                    {membersTradeDeal.date1 ? <TextFormat type="date" value={membersTradeDeal.date1} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {membersTradeDeal.date2 ? <TextFormat type="date" value={membersTradeDeal.date2} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{membersTradeDeal.long1}</td>
                  <td>{membersTradeDeal.string1}</td>
                  <td>{membersTradeDeal.boolean1 ? 'true' : 'false'}</td>
                  <td>
                    {membersTradeDeal.tradeShop ? (
                      <Link to={`trade-shop/${membersTradeDeal.tradeShop.id}`}>{membersTradeDeal.tradeShop.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${membersTradeDeal.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${membersTradeDeal.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${membersTradeDeal.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Members Trade Deals found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ membersTradeDeal }: IRootState) => ({
  membersTradeDealList: membersTradeDeal.entities,
  loading: membersTradeDeal.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MembersTradeDeal);
