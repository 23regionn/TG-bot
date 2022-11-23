import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tg-user-log.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITGUserLogDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TGUserLogDetail = (props: ITGUserLogDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tGUserLogEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tGUserLogDetailsHeading">TGUserLog</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{tGUserLogEntity.id}</dd>
          <dt>
            <span id="idTgUser">Id Tg User</span>
          </dt>
          <dd>{tGUserLogEntity.idTgUser}</dd>
          <dt>
            <span id="firstName">First Name</span>
          </dt>
          <dd>{tGUserLogEntity.firstName}</dd>
          <dt>
            <span id="registrationDate">Registration Date</span>
          </dt>
          <dd>
            {tGUserLogEntity.registrationDate ? (
              <TextFormat value={tGUserLogEntity.registrationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="userRole">User Role</span>
          </dt>
          <dd>{tGUserLogEntity.userRole}</dd>
          <dt>
            <span id="isAdmin">Is Admin</span>
          </dt>
          <dd>{tGUserLogEntity.isAdmin ? 'true' : 'false'}</dd>
          <dt>
            <span id="score">Score</span>
          </dt>
          <dd>{tGUserLogEntity.score}</dd>
          <dt>
            <span id="isBlocked">Is Blocked</span>
          </dt>
          <dd>{tGUserLogEntity.isBlocked ? 'true' : 'false'}</dd>
          <dt>
            <span id="chatId">Chat Id</span>
          </dt>
          <dd>{tGUserLogEntity.chatId}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{tGUserLogEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{tGUserLogEntity.date1 ? <TextFormat value={tGUserLogEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{tGUserLogEntity.date2 ? <TextFormat value={tGUserLogEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{tGUserLogEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{tGUserLogEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{tGUserLogEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>T G User</dt>
          <dd>{tGUserLogEntity.tGUser ? tGUserLogEntity.tGUser.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/tg-user-log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tg-user-log/${tGUserLogEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tGUserLog }: IRootState) => ({
  tGUserLogEntity: tGUserLog.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TGUserLogDetail);
