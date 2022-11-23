import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tg-user.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITGUserDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TGUserDetail = (props: ITGUserDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tGUserEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tGUserDetailsHeading">TGUser</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{tGUserEntity.id}</dd>
          <dt>
            <span id="idTgUser">Id Tg User</span>
          </dt>
          <dd>{tGUserEntity.idTgUser}</dd>
          <dt>
            <span id="firstName">First Name</span>
          </dt>
          <dd>{tGUserEntity.firstName}</dd>
          <dt>
            <span id="registrationDate">Registration Date</span>
          </dt>
          <dd>
            {tGUserEntity.registrationDate ? (
              <TextFormat value={tGUserEntity.registrationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="userRole">User Role</span>
          </dt>
          <dd>{tGUserEntity.userRole}</dd>
          <dt>
            <span id="isAdmin">Is Admin</span>
          </dt>
          <dd>{tGUserEntity.isAdmin ? 'true' : 'false'}</dd>
          <dt>
            <span id="score">Score</span>
          </dt>
          <dd>{tGUserEntity.score}</dd>
          <dt>
            <span id="isBlocked">Is Blocked</span>
          </dt>
          <dd>{tGUserEntity.isBlocked ? 'true' : 'false'}</dd>
          <dt>
            <span id="chatId">Chat Id</span>
          </dt>
          <dd>{tGUserEntity.chatId}</dd>
          <dt>
            <span id="isDelete">Is Delete</span>
            <UncontrolledTooltip target="isDelete">удаленный</UncontrolledTooltip>
          </dt>
          <dd>{tGUserEntity.isDelete ? 'true' : 'false'}</dd>
          <dt>
            <span id="date1">Date 1</span>
          </dt>
          <dd>{tGUserEntity.date1 ? <TextFormat value={tGUserEntity.date1} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="date2">Date 2</span>
          </dt>
          <dd>{tGUserEntity.date2 ? <TextFormat value={tGUserEntity.date2} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="long1">Long 1</span>
          </dt>
          <dd>{tGUserEntity.long1}</dd>
          <dt>
            <span id="string1">String 1</span>
          </dt>
          <dd>{tGUserEntity.string1}</dd>
          <dt>
            <span id="boolean1">Boolean 1</span>
          </dt>
          <dd>{tGUserEntity.boolean1 ? 'true' : 'false'}</dd>
          <dt>Balance</dt>
          <dd>{tGUserEntity.balance ? tGUserEntity.balance.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/tg-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tg-user/${tGUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tGUser }: IRootState) => ({
  tGUserEntity: tGUser.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TGUserDetail);
