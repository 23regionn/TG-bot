import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './admin.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdminDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdminDetail = (props: IAdminDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adminEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="adminDetailsHeading">Admin</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{adminEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{adminEntity.name}</dd>
          <dt>
            <span id="contact">Contact</span>
          </dt>
          <dd>{adminEntity.contact}</dd>
          <dt>
            <span id="link">Link</span>
          </dt>
          <dd>{adminEntity.link}</dd>
          <dt>
            <span id="isActive">Is Active</span>
          </dt>
          <dd>{adminEntity.isActive ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/admin" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/admin/${adminEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ admin }: IRootState) => ({
  adminEntity: admin.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdminDetail);
