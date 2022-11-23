import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './links-by-category-in-top-log.reducer';
import { ILinksByCategoryInTopLog } from 'app/shared/model/links-by-category-in-top-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILinksByCategoryInTopLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const LinksByCategoryInTopLog = (props: ILinksByCategoryInTopLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { linksByCategoryInTopLogList, match, loading } = props;
  return (
    <div>
      <h2 id="links-by-category-in-top-log-heading" data-cy="LinksByCategoryInTopLogHeading">
        Links By Category In Top Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Links By Category In Top Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {linksByCategoryInTopLogList && linksByCategoryInTopLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Category</th>
                <th>Price Diapozon</th>
                <th>Link</th>
                <th>Chanell Admin Id</th>
                <th>Date Post Link Start</th>
                <th>Date Post Link End</th>
                <th>Position Between Links</th>
                <th>Show Link</th>
                <th>Is Delete</th>
                <th>Date 1</th>
                <th>Date 2</th>
                <th>Long 1</th>
                <th>String 1</th>
                <th>Boolean 1</th>
                <th>Links By Category In Top</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {linksByCategoryInTopLogList.map((linksByCategoryInTopLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${linksByCategoryInTopLog.id}`} color="link" size="sm">
                      {linksByCategoryInTopLog.id}
                    </Button>
                  </td>
                  <td>{linksByCategoryInTopLog.category}</td>
                  <td>{linksByCategoryInTopLog.priceDiapozon}</td>
                  <td>{linksByCategoryInTopLog.link}</td>
                  <td>{linksByCategoryInTopLog.chanellAdminId}</td>
                  <td>
                    {linksByCategoryInTopLog.datePostLinkStart ? (
                      <TextFormat type="date" value={linksByCategoryInTopLog.datePostLinkStart} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {linksByCategoryInTopLog.datePostLinkEnd ? (
                      <TextFormat type="date" value={linksByCategoryInTopLog.datePostLinkEnd} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{linksByCategoryInTopLog.positionBetweenLinks}</td>
                  <td>{linksByCategoryInTopLog.showLink ? 'true' : 'false'}</td>
                  <td>{linksByCategoryInTopLog.isDelete ? 'true' : 'false'}</td>
                  <td>
                    {linksByCategoryInTopLog.date1 ? (
                      <TextFormat type="date" value={linksByCategoryInTopLog.date1} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {linksByCategoryInTopLog.date2 ? (
                      <TextFormat type="date" value={linksByCategoryInTopLog.date2} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{linksByCategoryInTopLog.long1}</td>
                  <td>{linksByCategoryInTopLog.string1}</td>
                  <td>{linksByCategoryInTopLog.boolean1 ? 'true' : 'false'}</td>
                  <td>
                    {linksByCategoryInTopLog.linksByCategoryInTop ? (
                      <Link to={`links-by-category-in-top/${linksByCategoryInTopLog.linksByCategoryInTop.id}`}>
                        {linksByCategoryInTopLog.linksByCategoryInTop.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`${match.url}/${linksByCategoryInTopLog.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${linksByCategoryInTopLog.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${linksByCategoryInTopLog.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Links By Category In Top Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ linksByCategoryInTopLog }: IRootState) => ({
  linksByCategoryInTopLogList: linksByCategoryInTopLog.entities,
  loading: linksByCategoryInTopLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(LinksByCategoryInTopLog);
