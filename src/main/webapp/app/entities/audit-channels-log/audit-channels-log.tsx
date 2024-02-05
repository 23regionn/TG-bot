import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './audit-channels-log.reducer';
import { IAuditChannelsLog } from 'app/shared/model/audit-channels-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAuditChannelsLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AuditChannelsLog = (props: IAuditChannelsLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { auditChannelsLogList, match, loading } = props;
  return (
    <div>
      <h2 id="audit-channels-log-heading" data-cy="AuditChannelsLogHeading">
        Audit Channels Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Audit Channels Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {auditChannelsLogList && auditChannelsLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Date Log</th>
                <th>Comment</th>
                <th>Contacts</th>
                <th>End Public Date</th>
                <th>Id Channel</th>
                <th>Is Moderate</th>
                <th>Is Pay</th>
                <th>Last Pay Date</th>
                <th>Link</th>
                <th>Name Channel</th>
                <th>Price For Pay</th>
                <th>Start Date</th>
                <th>Count Subscribers</th>
                <th>Count Views</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {auditChannelsLogList.map((auditChannelsLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${auditChannelsLog.id}`} color="link" size="sm">
                      {auditChannelsLog.id}
                    </Button>
                  </td>
                  <td>
                    {auditChannelsLog.dateLog ? (
                      <TextFormat type="date" value={auditChannelsLog.dateLog} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{auditChannelsLog.comment}</td>
                  <td>{auditChannelsLog.contacts}</td>
                  <td>
                    {auditChannelsLog.endPublicDate ? (
                      <TextFormat type="date" value={auditChannelsLog.endPublicDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{auditChannelsLog.idChannel}</td>
                  <td>{auditChannelsLog.isModerate ? 'true' : 'false'}</td>
                  <td>{auditChannelsLog.isPay ? 'true' : 'false'}</td>
                  <td>
                    {auditChannelsLog.lastPayDate ? (
                      <TextFormat type="date" value={auditChannelsLog.lastPayDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{auditChannelsLog.link}</td>
                  <td>{auditChannelsLog.nameChannel}</td>
                  <td>{auditChannelsLog.priceForPay}</td>
                  <td>
                    {auditChannelsLog.startDate ? (
                      <TextFormat type="date" value={auditChannelsLog.startDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{auditChannelsLog.countSubscribers}</td>
                  <td>{auditChannelsLog.countViews}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${auditChannelsLog.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${auditChannelsLog.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${auditChannelsLog.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Audit Channels Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ auditChannelsLog }: IRootState) => ({
  auditChannelsLogList: auditChannelsLog.entities,
  loading: auditChannelsLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AuditChannelsLog);
