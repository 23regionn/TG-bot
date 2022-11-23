import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './members-trade-deal-log.reducer';
import { IMembersTradeDealLog } from 'app/shared/model/members-trade-deal-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMembersTradeDealLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MembersTradeDealLog = (props: IMembersTradeDealLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { membersTradeDealLogList, match, loading } = props;
  return (
    <div>
      <h2 id="members-trade-deal-log-heading" data-cy="MembersTradeDealLogHeading">
        Members Trade Deal Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Members Trade Deal Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {membersTradeDealLogList && membersTradeDealLogList.length > 0 ? (
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
                <th>Members Trade Deal</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {membersTradeDealLogList.map((membersTradeDealLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${membersTradeDealLog.id}`} color="link" size="sm">
                      {membersTradeDealLog.id}
                    </Button>
                  </td>
                  <td>{membersTradeDealLog.tgUserIdCurrent}</td>
                  <td>{membersTradeDealLog.priceOffer}</td>
                  <td>
                    {membersTradeDealLog.currentDate ? (
                      <TextFormat type="date" value={membersTradeDealLog.currentDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{membersTradeDealLog.isWinner ? 'true' : 'false'}</td>
                  <td>{membersTradeDealLog.isDelete ? 'true' : 'false'}</td>
                  <td>
                    {membersTradeDealLog.date1 ? (
                      <TextFormat type="date" value={membersTradeDealLog.date1} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {membersTradeDealLog.date2 ? (
                      <TextFormat type="date" value={membersTradeDealLog.date2} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{membersTradeDealLog.long1}</td>
                  <td>{membersTradeDealLog.string1}</td>
                  <td>{membersTradeDealLog.boolean1 ? 'true' : 'false'}</td>
                  <td>
                    {membersTradeDealLog.membersTradeDeal ? (
                      <Link to={`members-trade-deal/${membersTradeDealLog.membersTradeDeal.id}`}>
                        {membersTradeDealLog.membersTradeDeal.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${membersTradeDealLog.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${membersTradeDealLog.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${membersTradeDealLog.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Members Trade Deal Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ membersTradeDealLog }: IRootState) => ({
  membersTradeDealLogList: membersTradeDealLog.entities,
  loading: membersTradeDealLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MembersTradeDealLog);
