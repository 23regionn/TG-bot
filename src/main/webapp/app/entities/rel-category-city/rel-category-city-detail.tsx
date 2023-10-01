import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './rel-category-city.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRelCategoryCityDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const RelCategoryCityDetail = (props: IRelCategoryCityDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { relCategoryCityEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="relCategoryCityDetailsHeading">RelCategoryCity</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{relCategoryCityEntity.id}</dd>
          <dt>
            <span id="isShow">Is Show</span>
          </dt>
          <dd>{relCategoryCityEntity.isShow ? 'true' : 'false'}</dd>
          <dt>
            <span id="score">Score</span>
          </dt>
          <dd>{relCategoryCityEntity.score}</dd>
          <dt>
            <span id="isFirst">Is First</span>
          </dt>
          <dd>{relCategoryCityEntity.isFirst ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/rel-category-city" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/rel-category-city/${relCategoryCityEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ relCategoryCity }: IRootState) => ({
  relCategoryCityEntity: relCategoryCity.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(RelCategoryCityDetail);
