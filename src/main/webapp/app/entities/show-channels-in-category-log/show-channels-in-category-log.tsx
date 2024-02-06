import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './show-channels-in-category-log.reducer';
import { IShowChannelsInCategoryLog } from 'app/shared/model/show-channels-in-category-log.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShowChannelsInCategoryLogProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ShowChannelsInCategoryLog = (props: IShowChannelsInCategoryLogProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const handleSyncList = () => {
    props.getEntities();
  };

  const { showChannelsInCategoryLogList, match, loading } = props;
  return (
    <div>
      <h2 id="show-channels-in-category-log-heading" data-cy="ShowChannelsInCategoryLogHeading">
        Show Channels In Category Logs
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh List
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Show Channels In Category Log
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {showChannelsInCategoryLogList && showChannelsInCategoryLogList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Id Channel</th>
                <th>Name Channel</th>
                <th>Id Category</th>
                <th>Name Category</th>
                <th>Is Show Channel</th>
                <th>Score Channel</th>
                <th>Comment</th>
                <th>Old Is Show Channel</th>
                <th>Old Score Channel</th>
                <th>Old Comment</th>
                <th>Date Log</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {showChannelsInCategoryLogList.map((showChannelsInCategoryLog, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${showChannelsInCategoryLog.id}`} color="link" size="sm">
                      {showChannelsInCategoryLog.id}
                    </Button>
                  </td>
                  <td>{showChannelsInCategoryLog.idChannel}</td>
                  <td>{showChannelsInCategoryLog.nameChannel}</td>
                  <td>{showChannelsInCategoryLog.idCategory}</td>
                  <td>{showChannelsInCategoryLog.nameCategory}</td>
                  <td>{showChannelsInCategoryLog.isShowChannel ? 'true' : 'false'}</td>
                  <td>{showChannelsInCategoryLog.scoreChannel}</td>
                  <td>{showChannelsInCategoryLog.comment}</td>
                  <td>{showChannelsInCategoryLog.oldIsShowChannel ? 'true' : 'false'}</td>
                  <td>{showChannelsInCategoryLog.oldScoreChannel}</td>
                  <td>{showChannelsInCategoryLog.oldComment}</td>
                  <td>
                    {showChannelsInCategoryLog.dateLog ? (
                      <TextFormat type="date" value={showChannelsInCategoryLog.dateLog} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`${match.url}/${showChannelsInCategoryLog.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${showChannelsInCategoryLog.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${showChannelsInCategoryLog.id}/delete`}
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
          !loading && <div className="alert alert-warning">No Show Channels In Category Logs found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ showChannelsInCategoryLog }: IRootState) => ({
  showChannelsInCategoryLogList: showChannelsInCategoryLog.entities,
  loading: showChannelsInCategoryLog.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShowChannelsInCategoryLog);
