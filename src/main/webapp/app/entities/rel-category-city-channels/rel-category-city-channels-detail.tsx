import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './rel-category-city-channels.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRelCategoryCityChannelsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelCategoryCityChannelsDetail = (props: IRelCategoryCityChannelsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { relCategoryCityChannelsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="relCategoryCityChannelsDetailsHeading">RelCategoryCityChannels</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{relCategoryCityChannelsEntity.id}</dd>
          <dt>
            <span id="scoreChannel">Score Channel</span>
          </dt>
          <dd>{relCategoryCityChannelsEntity.scoreChannel}</dd>
          <dt>
            <span id="isShowChannel">Is Show Channel</span>
          </dt>
          <dd>{relCategoryCityChannelsEntity.isShowChannel ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/rel-category-city-channels" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/rel-category-city-channels/${relCategoryCityChannelsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ relCategoryCityChannels }: IRootState) => ({
  relCategoryCityChannelsEntity: relCategoryCityChannels.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RelCategoryCityChannelsDetail);
