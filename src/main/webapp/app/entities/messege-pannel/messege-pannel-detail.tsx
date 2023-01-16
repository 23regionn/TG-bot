import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './messege-pannel.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMessegePannelDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MessegePannelDetail = (props: IMessegePannelDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { messegePannelEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="messegePannelDetailsHeading">MessegePannel</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{messegePannelEntity.id}</dd>
          <dt>
            <span id="idMessage">Id Message</span>
          </dt>
          <dd>{messegePannelEntity.idMessage}</dd>
          <dt>
            <span id="idChannel">Id Channel</span>
          </dt>
          <dd>{messegePannelEntity.idChannel}</dd>
          <dt>
            <span id="dateCreateMessage">Date Create Message</span>
          </dt>
          <dd>
            {messegePannelEntity.dateCreateMessage ? (
              <TextFormat value={messegePannelEntity.dateCreateMessage} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="textMessage">Text Message</span>
          </dt>
          <dd>{messegePannelEntity.textMessage}</dd>
          <dt>
            <span id="idAdmin">Id Admin</span>
          </dt>
          <dd>{messegePannelEntity.idAdmin}</dd>
          <dt>
            <span id="comment">Comment</span>
          </dt>
          <dd>{messegePannelEntity.comment}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{messegePannelEntity.status}</dd>
          <dt>
            <span id="serviceField1">Service Field 1</span>
          </dt>
          <dd>{messegePannelEntity.serviceField1}</dd>
          <dt>
            <span id="serviceField2">Service Field 2</span>
          </dt>
          <dd>{messegePannelEntity.serviceField2}</dd>
          <dt>
            <span id="serviceField3">Service Field 3</span>
          </dt>
          <dd>{messegePannelEntity.serviceField3}</dd>
        </dl>
        <Button tag={Link} to="/messege-pannel" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/messege-pannel/${messegePannelEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ messegePannel }: IRootState) => ({
  messegePannelEntity: messegePannel.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MessegePannelDetail);
